import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

public class PanelSetUp extends JPanel implements KeyListener, MouseListener {

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
        try {
            grid = ImageIO.read(new File("Visuals\\Outline (1).png"));
            title = ImageIO.read(new File("Visuals\\title  (1).png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTimer(){

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        makeFrame();
        super.paintComponent(g);

        g.drawString(String.valueOf(logic.getTime()), 10, 10);
        g.drawImage(grid, 200, 20, null);
        g.drawImage(title, 700 , 5, null);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}