import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
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

public class PanelSetUp extends JPanel implements KeyListener, ActionListener, MouseListener {
    private Rectangle rect1;
    private Timer timer;
    private int blockFallSpeed = 500;
    private int wave = 1;
    private int waveIncreaseInterval = 5;
    private Image[] blockImages = new Image[7];
    private Image blockImage;
    private String message;
    private String type;
    private Block block;
    private BufferedImage grid;
    private BufferedImage title;
    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private String name;
    private GameLogic logic;
    final int scale = 3;
    OutputWindow game;
    BossFight zaif;
    public boolean bossFightStarted;

    public PanelSetUp(GameLogic logic, BossFight zaif) throws IOException {
        logic = new GameLogic(this);
        textPane = new JTextPane(); // panel that can handle custom text
        textPane.setEditable(false); // prevents user from typing into window
        doc = textPane.getStyledDocument(); // call getter method for panel's style doc
        style = doc.addStyle("my style", null); // add a custom style to the doc
        StyleConstants.setFontSize(style, 25); // apply font size to custom style
        add(textPane); // add the panel to the frame
        this.logic = logic;
        this.zaif = zaif;
        makeFrame();
        type = generateBlock();
        block = logic.getCurrentBlock();
        setFocusable(true);
        addKeyListener(this);
    }

    public void makeFrame() {
        try {
            grid = ImageIO.read(new File("Visuals\\Outline (1).png"));
            title = ImageIO.read(new File("Visuals\\title  (1).png"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }



        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
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
        Block b = logic.getCurrentBlock();
        g.drawImage(b.getImage(), b.getX(), b.getY(), this);
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
        if (key == KeyEvent.VK_UP) {  // up key// .rotateClockwise();
            logic.rotateBlock();
        } else if (key == KeyEvent.VK_DOWN) { // down key
            logic.moveBlockDown();
        } else if (key == KeyEvent.VK_LEFT) {
            logic.moveBlockLeft();
        } else {
            logic.moveBlockRight();
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

    private String generateBlock(){
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}