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

public class BossFight implements KeyListener, MouseListener {

    private BufferedImage zaif;
    Music music;
    private PanelSetUp panel;


    public BossFight(PanelSetUp panel) throws IOException {
        this.panel = panel;
        makeImage();
    }

    public void start(){
        for (int i = 0; i < GameLogic.HEIGHT; i++)
            for (int j = 0; j < GameLogic.WIDTH; j++)
                panel.logic.grid[i][j] = 0;
        panel.blockTimer.setDelay(50);
        music = new Music("sounds/BARNEY.wav");
        music.setFile();
        music.loop();
    }

    public void endMusic(){
        music.stop();
        music.close();
    }

    public BufferedImage getZaif() {
        return zaif;
    }

    public void makeImage() throws IOException {
        zaif = ImageIO.read(getClass().getResource("Visuals/ZAIFBOSSFIGHT.png"));
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
