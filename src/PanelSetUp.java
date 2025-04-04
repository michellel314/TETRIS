import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.Point;
import javax.swing.Timer;

public class PanelSetUp extends JPanel implements KeyListener {
    private Timer timer;
    Timer blockTimer;
    private int blockFallSpeed = 500;
    private Image[] blockImages = new Image[7];
    private String type;
    private Block block;
    private BufferedImage grid;
    private BufferedImage title;
    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private GameLogic logic;
    final int scale = 3;
    private OutputWindow game;
    private BossFight zaif;
    public boolean bossFightStarted;
    public Boolean isShopOpen;
    private Shop shop;
    public Boolean gameRunning;
    private Equipment[] equips;

    public PanelSetUp(GameLogic logic, BossFight zaif, Shop shop) {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        loadBlockImages();
        this.shop = shop;
        this.logic = logic;
        this.zaif = zaif;
        textPane = new JTextPane(); // panel that can handle custom text
        textPane.setEditable(false); // prevents user from typing into window
        doc = textPane.getStyledDocument(); // call getter method for panel's style doc
        style = doc.addStyle("my style", null); // add a custom style to the doc
        StyleConstants.setFontSize(style, 25); // apply font size to custom style
        add(textPane); // add the panel to the frame
        logic.spawnBlock();
        makeFrame();
        type = generateBlock();
        block = new Block(type);
        isShopOpen = false;
        gameRunning = true;
        blockTimer = new Timer(500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                boolean stillMoving = logic.moveBlockDown();
                if(!stillMoving){
                    logic.spawnBlock();
                }
                repaint();
            }
        });

        timer.start();


    }

    public void makeFrame() {
        try {
            grid = ImageIO.read(new File("Visuals\\Outline (1).png"));
            title = ImageIO.read(new File("Visuals\\title  (1).png"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


        // Debugging: Confirm image loading
        if (grid == null) {
            System.out.println("Error: Grid image not found.");
        }
        if (title == null) {
            System.out.println("Error: Title image not found.");
        }
        timer = new Timer(blockFallSpeed, e -> moveBlockDown());
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void moveBlockDown() {
        block.moveDown();
        repaint();
    }

    private void loadBlockImages() {
        String type = generateBlock();
        block = new Block(type);
        timer = new Timer (500, e -> moveBlockDown());
        timer.start();
    }

    public String generateBlock(){
        int num = (int) (Math.random() * 6) + 1;
        if(num == 1){
            return "A";
        } else if (num == 2){
            return "B";
        } else if (num == 3){
            return "C";
        } else if (num == 4){
            return "D";
        } else if (num == 5){
            return "E";
        } else if (num == 6){
            return "F";
        } else {
            return "G";
        }
    }

    public void updateTimer() throws IOException {
        repaint();
            if (logic.getTime() == 20) {
                logic.music.stop();
                bossFightStarted = true;
                logic.startBossFight();
            }

            if (logic.getTime() == 5) {
                logic.openShop();
            }

            if (logic.getTime() == 15) {
                logic.closeShop();
            }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(String.valueOf(logic.getTime()), 10, 1000);
        for(int row = 0; row < 17; row++){
            for(int col = 0; col < 9; col++){
                if(logic.grid[row][col] != 0){
                    g.drawImage(block.getImage(), col * 25, row * 25, null);
                }
            }
        }
        if(logic.getCurrentBlock() != null){
            g.drawImage(block.getImage(), block.getX(), block.getY(), null);
        }
        if (block != null) {
            g.drawImage(block.getImage(), block.getX(), block.getY(), null);
        }
        // Draw score
        g.drawString("Score: " + logic.getScore(), 10, 10);

        if (bossFightStarted){
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {  // up key
            block.rotateClockwise();
        } else if (key == KeyEvent.VK_DOWN) { // down key
            block.moveDown();
        } else if (key == KeyEvent.VK_LEFT) {
            block.moveLeft();
        } else if (key == KeyEvent.VK_RIGHT){
            block.moveRight();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}