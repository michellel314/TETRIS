import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ReyvinsQuirkyStunGun extends Equipment{
    private int pauseDuration;
    public ReyvinsQuirkyStunGun (BufferedImage file, String name, int score, Boolean existsInInv, int pauseDuration, ActionListener actionListener){
        super(file, name, score, existsInInv, actionListener);
        this.pauseDuration = pauseDuration;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }
}
