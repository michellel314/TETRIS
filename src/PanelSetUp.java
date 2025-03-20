import javax.swing.JPanel;
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

public class PanelSetUp extends JPanel implements KeyListener, MouseListener {
    private int rectX;
    private int rectY;
    private Rectangle rect1;

    // add second rectangle and its x and y location as instance variables
    private int rect2X;
    private int rect2Y;
    private Rectangle rect2;

    private String message;
    private Color rectColor;

    public void makeFrame() {

        OutputWindow game = new OutputWindow("Teris");

        rect1 = new Rectangle(100, 100);

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
        int currentX = (int) rect1.getX();
        int currentY = (int) rect1.getY();

        int key = e.getKeyCode();
        if (key == 38) {  // up key
            rectY = currentY - 5;
        } else if (key == 40) { // down key
            rectY = currentY + 5;
        } else if (key == 37) { // left key
            rectX = currentX - 5;
        } else if (key == 39) {  // right key
            rectX = currentX + 5;
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
}