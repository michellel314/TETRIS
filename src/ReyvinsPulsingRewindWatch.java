import java.io.File;

public class ReyvinsPulsingRewindWatch extends Equipment{
    private int rewind;
    public ReyvinsPulsingRewindWatch(File file, String name, int score, int uses, int rewind){
        super(file, name, score, uses);
        this.rewind = rewind;
    }

    public int getRewind() {
        return rewind;
    }
}
