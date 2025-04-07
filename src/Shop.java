import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shop implements KeyListener, MouseListener , ActionListener{


    public ReyvinsGirlyPopHighHeels highheels;
    public ReyvinsPulsingRewindWatch watch;
    public ReyvinsQuirkyStunGun gun;
    public ReyvinsStiffShovel shovel;
    public ActionListener button;
    private BufferedImage shop;
    private BufferedImage reyvin;
    private Player player;
    private PanelSetUp panel;

    public Shop(Player player, PanelSetUp panel) {
        this.panel = panel;
        makeEquipment();
    }

    public void makeEquipment(){
        try {
            highheels = new ReyvinsGirlyPopHighHeels(ImageIO.read(getClass().getResource("Visuals/HighHeels.png")), "Reyvin's Girly-Pop High Heels", 50, false, 50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.watchClicked = false;
                    panel.gunClicked = false;
                    panel.shovelClicked = false;
                    panel.heelsClicked = true;
                }
            });
            watch = new ReyvinsPulsingRewindWatch(ImageIO.read(getClass().getResource("Visuals/Watch.png")), "Reyvin's Pulsing Rewind Watch", 75, false, 1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.gunClicked = false;
                    panel.shovelClicked = false;
                    panel.heelsClicked = false;
                    panel.watchClicked = true;
                }
            });
            gun = new ReyvinsQuirkyStunGun(ImageIO.read(getClass().getResource("Visuals/Gun.png")), "Reyvin's Quirky Stun Gun", 80, false, 5, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.shovelClicked = false;
                    panel.heelsClicked = false;
                    panel.watchClicked = false;
                    panel.gunClicked = true;
                }
            });
            shovel = new ReyvinsStiffShovel(ImageIO.read(getClass().getResource("Visuals/Shovel.png")), "Reyvin's Stiff Shovel", 50, false, 1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.heelsClicked = false;
                    panel.watchClicked = false;
                    panel.gunClicked = false;
                    panel.shovelClicked = true;
                }
            });
            shop = ImageIO.read(getClass().getResource("Visuals/ShopBackground.png"));
            reyvin = ImageIO.read(getClass().getResource("Visuals/Reyvin.png"));
            this.player = player;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getShop() {
        return shop;
    }
    public void setPanel(PanelSetUp panel){
        this.panel = panel;
    }

    public BufferedImage getReyvin(){
        return reyvin;
    }

    public void buyShovel() {
        player.setScore(player.getScore() - shovel.getScore());
        shovel.existsInInv = true;
        player.addToInventory(shovel);
    }
    public void buyGun() {
        player.setScore(player.getScore() - gun.getScore());
        gun.existsInInv = true;
        player.addToInventory(gun);
    }
    public void buyHeels() {
        player.setScore(player.getScore() - highheels.getScore());
        highheels.existsInInv = true;
        player.addToInventory(highheels);
    }
    public void buyWatch() {
        player.setScore(player.getScore() - watch.getScore());
        watch.existsInInv = true;
        player.addToInventory(watch);
    }

    public void openShop(){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.logic.closeShop();
        panel.button.setVisible(false);
    }
}
