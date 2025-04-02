import java.io.File;

public class ReyvinsQuirkyStunGun extends Equipment{
    private int pauseDuration;
    public ReyvinsQuirkyStunGun (File file, String name, int score, int uses, int pauseDuration){
        super(file, name, score, uses);
        this.pauseDuration = pauseDuration;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }
}
