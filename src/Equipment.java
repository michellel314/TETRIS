import java.awt.image.BufferedImage;
import java.io.File;

public class Equipment{

    public Boolean existsInInv;
    String name;
    int score;
    BufferedImage file;

    public Equipment(BufferedImage file, String name, int score, Boolean existsInInv) {
        this.existsInInv = existsInInv;
        this.name = name;
        this.score = score;
        this.file = file;
    }

    public Boolean getExistsInInv() {
        return existsInInv;
    }

    public BufferedImage getFile(){
        return file;
    }

    public String equipmentUsed() {
        existsInInv = false;
        return "you used " + name;
    }

}
