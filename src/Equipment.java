import java.io.File;

public class Equipment{

    public Boolean existsInInv;
    String name;
    int score;
    File file;

    public Equipment(File file, String name, int score, Boolean existsInInv) {
        this.existsInInv = existsInInv;
        this.name = name;
        this.score = score;
        this.file = file;
    }

    public Boolean getExistsInInv() {
        return existsInInv;
    }

    public File getFile(){
        return file;
    }

    public String equipmentUsed() {
        existsInInv = false;
        return "you used " + name;
    }

}
