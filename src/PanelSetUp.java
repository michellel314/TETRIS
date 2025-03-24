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

public class PanelSetUp extends JPanel implements KeyListener, MouseListener {

    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    private String name;
    private BufferedImage grid;

    public PanelSetUp(){
        textPane = new JTextPane(); // panel that can handle custom text
        textPane.setEditable(false); // prevents user from typing into window
        doc = textPane.getStyledDocument(); // call getter method for panel's style doc
        style = doc.addStyle("my style", null); // add a custom style to the doc
        StyleConstants.setFontSize(style, 25); // apply font size to custom style
        add(textPane); // add the panel to the frame
    }

    public void makeFrame() {
        try {
            grid = ImageIO.read(new File("Visuals\\grid.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        rect1.setLocation(300, 300);
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLACK);
        g2d.fill(rect1);

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