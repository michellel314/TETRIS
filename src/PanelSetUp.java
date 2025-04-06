import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelSetUp extends JPanel implements KeyListener {
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
    private int blockFallSpeed = 500;

    public PanelSetUp(GameLogic logic, Shop shop) throws IOException {
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
        if (logic.getTime() == 5) logic.openShop();
        if (logic.getTime() == 15) logic.closeShop();
        if (logic.getTime() == 20) {
            logic.music.stop();
            bossFightStarted = true;
            //zaif.start();
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
            g.drawImage(shop.getShop(), 200, 0, null);
            g.drawImage(shop.getReyvin(), 700, 300, null);
            g.setFont(new Font("Ancient", Font.BOLD, 50));
            g.setColor(Color.pink);
            g.drawString("Buy something.", 600, 200);
            g.drawString("Stop wasting my time.", 650, 275);
            g.drawImage(shop.highheels.getFile(), 300, 640, null);
            g.drawImage(shop.gun.getFile(), 600, 700, null);
            g.drawImage(shop.shovel.getFile(), 1000, 700, null);
            g.drawImage(shop.watch.getFile(), 1200, 600, null);
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
}
