import java.io.File;

public class ReyvinsGirlyPopHighHeels extends Equipment{
    private int skipChance;
    public ReyvinsGirlyPopHighHeels(File file, String name, int score, int uses, int skipChance){
        super(file, name, score, uses);
        this.skipChance = skipChance;
    }

    public int getSkipChance(){
        return skipChance;
    }

}
