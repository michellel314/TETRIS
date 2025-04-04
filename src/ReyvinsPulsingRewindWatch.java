import java.awt.image.BufferedImage;
import java.io.File;

public class ReyvinsPulsingRewindWatch extends Equipment{
    private int rewind;
    public ReyvinsPulsingRewindWatch(BufferedImage file, String name, int score, Boolean existsInInv, int rewind){
        super(file, name, score, existsInInv);
        this.rewind = rewind;
    }

    public int getRewind() {
        return rewind;
    }
}
