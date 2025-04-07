import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ReyvinsStiffShovel extends Equipment{
    private int numberOfRows;
    public ReyvinsStiffShovel(BufferedImage file, String name, int score, Boolean existsInInv, int numberOfRows, ActionListener actionListener){
        super(file, name, score, existsInInv, actionListener);
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
