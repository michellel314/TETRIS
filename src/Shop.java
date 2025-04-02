import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shop extends JPanel implements KeyListener, MouseListener {


    private ReyvinsGirlyPopHighHeels highheels;
    private ReyvinsPulsingRewindWatch watch;
    private ReyvinsQuirkyStunGun gun;
    private ReyvinsStiffShovel shovel;
    private BufferedImage shop;

    public Shop() throws IOException {
        highheels = new ReyvinsGirlyPopHighHeels(new File("Visuals\\HighHeels.png"),"Reyvin's Girly-Pop High Heels", 50, 1, 50);
        watch = new ReyvinsPulsingRewindWatch(new File("Visuals\\Watch.png"), "Reyvin's Pulsing Rewind Watch", 75, 1, 1);
        gun = new ReyvinsQuirkyStunGun(new File("Visuals\\Gun.png"), "Reyvin's Quirky Stun Gun", 80, 3, 5);
        shovel = new ReyvinsStiffShovel(new File("Visuals\\Shovel.png"), "Reyvin's Stiff Shovel", 50, 1, 1);
        BufferedImage shop = ImageIO.read(new File("Visuals\\ShopBackground.png"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(shop, 200, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
}
