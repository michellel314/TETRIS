import java.io.File;

public class ReyvinsGirlyPopHighHeels extends Equipment{
    private int skipChance;
    public ReyvinsGirlyPopHighHeels(File file, String name, int score, Boolean existsInInv, int skipChance){
        super(file, name, score, existsInInv);
        this.skipChance = skipChance;
    }

    public int getSkipChance(){
        return skipChance;
    }

}
