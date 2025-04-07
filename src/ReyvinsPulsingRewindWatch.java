import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ReyvinsPulsingRewindWatch extends Equipment{
    private int rewind;
    public ReyvinsPulsingRewindWatch(BufferedImage file, String name, int score, Boolean existsInInv, int rewind, ActionListener actionListener){
        super(file, name, score, existsInInv, actionListener);
        this.rewind = rewind;
    }

    public int getRewind() {
        return rewind;
    }
}
