import java.io.File;

public class ReyvinsQuirkyStunGun extends Equipment{
    private int pauseDuration;
    public ReyvinsQuirkyStunGun (File file, String name, int score, Boolean existsInInv, int pauseDuration){
        super(file, name, score, existsInInv);
        this.pauseDuration = pauseDuration;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }
}
