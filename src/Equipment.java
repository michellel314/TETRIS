import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Equipment implements MouseListener {

    public boolean existsInInv;
    String name;
    int score;
    BufferedImage file;
    ActionListener button;

    public Equipment(BufferedImage file, String name, int score, Boolean existsInInv, ActionListener button) {
        this.existsInInv = existsInInv;
        this.name = name;
        this.score = score;
        this.file = file;
        this.button = button;
    }

    public boolean getExistsInInv() {
        return existsInInv;
    }

    public BufferedImage getFile(){
        return file;
    }

    public String equipmentUsed() {
        existsInInv = false;
        return "you used " + name;
    }

    public int getScore() {
        return score;
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
