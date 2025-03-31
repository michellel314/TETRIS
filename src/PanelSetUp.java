import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

public class PanelSetUp extends JPanel implements KeyListener, MouseListener {
    private int rectX;
    private int rectY;
    private Rectangle rect1;
    private Timer timer;
    private int blockFallSpeed = 500;
    private int wave = 1;
    private int waveIncreaseInterval = 5;
    // add second rectangle and its x and y location as instance variables
    private int rect2X;
    private int rect2Y;
    private Rectangle rect2;
    private Image[] blockImages = new Image[18];
    private Image blockImage;
    private String message;
    private Color rectColor;
    private Block block;

    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private String name;
    private BufferedImage grid;
    private BufferedImage title;
    private GameLogic logic;
    Music music = new Music();
    final int scale = 3;

    public void setUpGame(){
        music.setFile(0);
        music.play();
    }
    public PanelSetUp(GameLogic logic){
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

        rect1 = new Rectangle(100, 100);
        rectX = 300;
        rectY = 0;

        timer = new Timer(blockFallSpeed, e -> moveBlockDown());
        timer.start();
        // initialize second rectangle and its x and y location
        rect2X = 230;
        rect2Y = 5;
        rect2 = new Rectangle(20, 20);

        message = "mouse click: ";
        rectColor = Color.RED;

        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void loadBlockImages(){
        try{
            blockImages[0] = ImageIO.read(new File("Visuals/BlueBlock(1).png"));
            blockImages[1] = ImageIO.read(new File("Visuals/BlueBlock(2).png"));
            blockImages[2] = ImageIO.read(new File("Visuals/BlueBlock(3).png"));
            blockImages[3] = ImageIO.read(new File("Visuals/BlueBlock(4).png"));
            blockImages[4] = ImageIO.read(new File("Visuals/CyanBlock(1).png"));
            blockImages[5] = ImageIO.read(new File("Visuals/CyanBlock(2).png"));
            blockImages[6] = ImageIO.read(new File("Visuals/GreenBlock(1).png"));
            blockImages[7] = ImageIO.read(new File("Visuals/GreenBlock(2).png"));
            blockImages[8] = ImageIO.read(new File("Visuals/OrangeBlock(1).png"));
            blockImages[9] = ImageIO.read(new File("Visuals/OrangeBlock(2).png"));
            blockImages[10] = ImageIO.read(new File("Visuals/PurpleBlock(1).png"));
            blockImages[11] = ImageIO.read(new File("Visuals/PurpleBlock(2).png"));
            blockImages[12] = ImageIO.read(new File("Visuals/PurpleBlock(3).png"));
            blockImages[13] = ImageIO.read(new File("Visuals/PurpleBlock(4).png"));
            blockImages[14] = ImageIO.read(new File("Visuals/Red Block (4)(1).png"));
            blockImages[15] = ImageIO.read(new File("Visuals/Red Block (4)(5).png"));
            blockImages[16] = ImageIO.read(new File("Visuals/Red Block (4)(6).png"));
            blockImages[17] = ImageIO.read(new File("Visuals/Red Block (4)(7).png"));
            blockImages[18] = ImageIO.read(new File("Visuals/Yellow Block.png"));

            blockImage = blockImages[0];
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        makeFrame();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        rect1.setLocation(rectX, rectY);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.fill(rect1);

        g.drawString(String.valueOf(logic.getTime()), 10, 10);
        g.drawImage(grid, 200, 20, null);
        g.drawImage(title, 700 , 5, null);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int currentX = (int) rect1.getX();
        int currentY = (int) rect1.getY();

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {  // up key
           block.rotate();
        } else if (key == KeyEvent.VK_DOWN) { // down key
            block.moveDown();
        } else if (key == KeyEvent.VK_LEFT) { // left key
            block.moveLeft();
        } else if (key == KeyEvent.VK_RIGHT) {  // right key
            block.moveRight();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point mouseClickLocation = e.getPoint();
            message = "mouse click: (" + mouseClickLocation.getX() + ", " + mouseClickLocation.getY() + ")";
            if (rect1.contains(mouseClickLocation)) {
                rectColor = Color.GREEN;
            } else {
                rectColor = Color.RED;
            }

            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    private void moveBlockDown(){
        rectY += 30;
        if(rectY + rect1.getHeight() >= getHeight()){
            rectY = getHeight() - (int) rect1.getHeight();
            timer.stop();
        }
        repaint();
    }

    private void spawnNewBlock(){
        rectX = 300;
        rectY = 0;
        rect1.setLocation(rectX, rectY);
    }

}