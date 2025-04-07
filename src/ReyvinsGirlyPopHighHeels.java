import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class ReyvinsGirlyPopHighHeels extends Equipment implements ActionListener{
    private int skipChance;
    public ReyvinsGirlyPopHighHeels(BufferedImage file, String name, int score, Boolean existsInInv, int skipChance, ActionListener button){
        super(file, name, score, existsInInv, button);
        this.skipChance = skipChance;
    }

    public int getSkipChance(){
        return skipChance;
    }

    @Override
    public String equipmentUsed() {
        existsInInv = false;
        return "your " + name + " exploded.";
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
