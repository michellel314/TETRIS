import java.awt.image.BufferedImage;
import java.io.File;

public class ReyvinsStiffShovel extends Equipment{
    private int numberOfRows;
    public ReyvinsStiffShovel(BufferedImage file, String name, int score, Boolean existsInInv, int numberOfRows){
        super(file, name, score, existsInInv);
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
