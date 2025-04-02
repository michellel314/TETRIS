import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class PanelSetUp extends JPanel implements KeyListener, MouseListener {
    private Rectangle rect1;
    private Timer timer;
    private int blockFallSpeed = 500;
    private int wave = 1;
    private int waveIncreaseInterval = 5;
    private Image[] blockImages = new Image[7];
    private Image blockImage;
    private String message;
    private Block block;
    private BufferedImage grid;
    private BufferedImage title;
    private BufferedImage reyvin;
    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private String name;
    private GameLogic logic;
    final int scale = 3;


    public PanelSetUp(GameLogic logic) {
        textPane = new JTextPane(); // panel that can handle custom text
        textPane.setEditable(false); // prevents user from typing into window
        doc = textPane.getStyledDocument(); // call getter method for panel's style doc
        style = doc.addStyle("my style", null); // add a custom style to the doc
        StyleConstants.setFontSize(style, 25); // apply font size to custom style
        add(textPane); // add the panel to the frame
        this.logic = logic;
    }

    public void makeFrame() {
        OutputWindow game = new OutputWindow("Teris");
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

        message = "mouse click: ";

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    private void moveBlockDown() {
    }

    public void loadBlockImages() {
        try {
            blockImages[0] = ImageIO.read(new File("Visuals\\BlueBlock(1).png"));
            blockImages[1] = ImageIO.read(new File("Visuals\\CyanBlock(1).png"));
            blockImages[2] = ImageIO.read(new File("Visuals\\GreenBlock(1).png"));
            blockImages[3] = ImageIO.read(new File("Visuals\\OrangeBlock(1).png"));
            blockImages[4] = ImageIO.read(new File("Visuals\\PurpleBlock(1).png"));
            blockImages[5] = ImageIO.read(new File("Visuals\\Red Block(1).png"));
            blockImages[6] = ImageIO.read(new File("Visuals\\Yellow Block.png"));

            blockImage = blockImages[0];
        } catch (IOException e) {
            System.out.println("Error loading block images: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void updateTimer() throws IOException {
        repaint();
        if (logic.getTime() == 5) {
            logic.music.stop();
            logic.startBossFight();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        makeFrame();
        super.paintComponent(g);
        g.drawString(String.valueOf(logic.getTime()), 10, 10);
        g.drawImage(grid, 200, 20, null);
        g.drawImage(title, 700, 5, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int currentX = (int) rect1.getX();
        int currentY = (int) rect1.getY();

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {  // up key
            block.rotate();
        } else if (key == KeyEvent.VK_DOWN) { // down key
            block.moveDown();
        } else if (key == KeyEvent.VK_LEFT) {
            block.moveLeft();
        } else {
            block.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void generateBlock(Graphics g){
        int number = (int) (Math.random() * 6) + 1;
        if(number == 1){
            g.drawImage(blockImages[0], 700, 10, null);
        } else if (number == 2){
            g.drawImage(blockImages[1], 700, 10, null);
        } else if (number == 3){
            g.drawImage(blockImages[2], 700, 10, null);
        } else if (number == 4){
            g.drawImage(blockImages[3], 700, 10, null);
        } else if (number == 5){
            g.drawImage(blockImages[4], 700, 10, null);
        } else if (number == 6){
            g.drawImage(blockImages[5], 700, 10, null);
        } else {
            g.drawImage(blockImages[6], 700, 10, null);
        }
    }
}