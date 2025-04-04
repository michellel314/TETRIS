import javax.swing.*;
import java.awt.*;
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
    OutputWindow game;
    BossFight zaif;
    Shop shop;
    public boolean bossFightStarted;

    public PanelSetUp(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        loadBlockImages();
    }
    public PanelSetUp(GameLogic logic, BossFight zaif, Shop shop) {
        this.shop = shop;
        this.logic = logic;
        this.zaif = zaif;
    }

    public void makeFrame() {
        try {
            grid = ImageIO.read(new File("Visuals/Outline (1).png"));
            title = ImageIO.read(new File("Visuals/title  (1).png"));
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


    public void setLogic(GameLogic logic, BossFight zaif, Shop shop){
        this.logic = logic;
        this.zaif = zaif;
        this.shop = shop;
        textPane = new JTextPane(); // panel that can handle custom text
        textPane.setEditable(false); // prevents user from typing into window
        doc = textPane.getStyledDocument(); // call getter method for panel's style doc
        style = doc.addStyle("my style", null); // add a custom style to the doc
        StyleConstants.setFontSize(style, 25); // apply font size to custom style
        add(textPane); // add the panel to the frame

        makeFrame();
        type = generateBlock();
        block = new Block(type); // Make sure this is creating the block correctly
    }

    public void updateTimer() throws IOException {
        repaint();
        if (logic.getTime() == 5) {
            logic.music.stop();
            bossFightStarted = true;
            logic.startBossFight();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(String.valueOf(logic.getTime()), 10, 10);
        g.drawImage(grid, 200, 20, null);
        g.drawImage(title, 725, 5, null);
        if (block != null) {
            g.drawImage(block.getImage(), block.getX(), block.getY(), null);
        }

        // Draw score
        g.drawString("Score: " + logic.getScore(), 10, 10);
        if (bossFightStarted){
            zaif.paintComponent(g);
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