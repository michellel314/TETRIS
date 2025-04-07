import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelSetUp extends JPanel implements KeyListener, ActionListener{
    private Timer blockTimer;
    private Image[] blockImages = new Image[7];
    private Block block;
    private BufferedImage grid, title;
    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private GameLogic logic;
    private OutputWindow game;
    private BossFight zaif;
    private Shop shop;
    public boolean bossFightStarted;
    public Boolean isShopOpen;
    public Boolean gameRunning;
    private int[] xs;
    private int[] ys;
    private int blockFallSpeed = 500;
    private Button button;

    public PanelSetUp(GameLogic logic, Shop shop) throws IOException{
        this.logic = logic;
        this.zaif = new BossFight(this);
        this.shop = shop;
        setFocusable(true);
        addKeyListener(this);
        loadBlockImages();
        setupTextPane();
        setupImages();
        gameRunning = true;
        isShopOpen = false;
        bossFightStarted = false;
        button = new Button("Close Shop");
        button.addActionListener(this);
        add(button);
        button.setVisible(false);

        blockTimer = new Timer(blockFallSpeed, e -> {
            boolean moving = logic.moveBlockDown();
            if (!moving) logic.spawnBlock();
            repaint();
        });
        blockTimer.start();
    }

    private void setupTextPane() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        doc = textPane.getStyledDocument();
        style = doc.addStyle("my style", null);
        StyleConstants.setFontSize(style, 25);
        add(textPane);
    }

    private void setupImages() {
        try {
            grid = ImageIO.read(new File("Visuals/Outline (1).png"));
            title = ImageIO.read(new File("Visuals/title  (1).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBlockImages() {
        for (int i = 0; i < blockImages.length; i++) {
            blockImages[i] = new ImageIcon("Visuals/Block" + i + ".png").getImage();
        }
    }


    public void updateTimer() throws IOException {
        repaint();
        if (logic.getTime() == 5) {
            button.setVisible(true);
            logic.openShop();
        }
        if (logic.getTime() == 20) {
            logic.music.stop();
            bossFightStarted = true;
            zaif.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Score: " + logic.getScore(), 10, 10);
        g.drawString(String.valueOf(logic.getTime()), 10, 1000);

        // Draw placed blocks
        for (int row = 0; row < GameLogic.HEIGHT; row++) {
            for (int col = 0; col < GameLogic.WIDTH; col++) {
                if (logic.grid[row][col] != 0) {
                    g.fillRect(col * 25, row * 25, 25, 25);
                }
            }
        }

        Block current = logic.getCurrentBlock();
        if (current != null) {
            g.drawImage(current.getImage(), current.getX(), current.getY(), null);
        }

        if (bossFightStarted) {
            g.drawImage(zaif.getZaif(), 10, 10, null);
        }

        if (isShopOpen) {

            xs = new int[]{1100, 1050, 1000};
            ys = new int[]{300, 300, 380};
            g.drawImage(shop.getShop(), 200, 0, null);
            g.drawImage(shop.getReyvin(), 700, 300, null);
            g.setColor(Color.white);
            g.drawOval(610, 130, 600, 200);
            g.drawPolygon(xs, ys, 3);
            g.fillPolygon(xs, ys, 3);
            g.fillOval(610, 130, 600, 200);
            g.setFont(new Font("Times New Roman", Font.BOLD, 45));
            g.setColor(Color.magenta);

            if (shop.highheels.getExistsInInv() && shop.shovel.getExistsInInv() && shop.gun.getExistsInInv() && shop.watch.getExistsInInv()) {
                g.drawString("I got nothing to sell you.", 660, 250);
            } else {
                g.drawString("Buy something.", 730, 200);
                g.drawString("Stop wasting my time.", 680, 275);
                if (!shop.highheels.getExistsInInv()) {
                    g.drawImage(shop.highheels.getFile(), 300, 640, null);
                }
                if (!shop.shovel.getExistsInInv()) {
                    g.drawImage(shop.shovel.getFile(), 1000, 700, null);
                }
                if (!shop.gun.getExistsInInv()){
                    g.drawImage(shop.gun.getFile(), 600, 700, null);
                }
                if (!shop.watch.getExistsInInv()) {
                    g.drawImage(shop.watch.getFile(), 1200, 600, null);
                }
            }
            button.setVisible(true);
            button.setLocation(1000, 100);
        }

        if (gameRunning) {
            g.drawImage(grid, 200, 20, null);
            g.drawImage(title, 725, 5, null);
        }
    }


    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Block current = logic.getCurrentBlock();
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) current.rotateClockwise();
        if (key == KeyEvent.VK_DOWN) current.moveDown();
        if (key == KeyEvent.VK_LEFT) current.moveLeft();
        if (key == KeyEvent.VK_RIGHT) current.moveRight();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logic.closeShop();
        button.setVisible(false);
    }
}
