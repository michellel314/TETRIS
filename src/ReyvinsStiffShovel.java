import java.io.File;

public class ReyvinsStiffShovel extends Equipment{
    private int numberOfRows;
    public ReyvinsStiffShovel(File file, String name, int score, int uses, int numberOfRows){
        super(file, name, score, uses);
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
