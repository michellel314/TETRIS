import java.awt.image.BufferedImage;
import java.io.File;

public class Equipment{

    public boolean existsInInv;
    String name;
    int score;
    BufferedImage file;

    public Equipment(BufferedImage file, String name, int score, Boolean existsInInv) {
        this.existsInInv = existsInInv;
        this.name = name;
        this.score = score;
        this.file = file;
    }

    public boolean getExistsInInv() {
        return existsInInv;
    }

    public BufferedImage getFile(){
        return file;
    }

    public String equipmentUsed() {
        existsInInv = false;
        return "you used " + name;
    }

    public int getScore() {
        return score;
    }
}
