import java.io.File;

public class Equipment{

    private int uses;
    String name;
    int score;
    File file;

    public Equipment(File file, String name, int score, int uses) {
        this.uses = uses;
        this.name = name;
        this.score = score;
        this.file = file;
    }

    public int getUses() {
        return uses;
    }

    public File getFile(){
        return file;
    }

    public String equipmentUsed() {
        if (uses == 0) {
            return name + " is broken.";
        } else {
            uses--;
            return "you used " + name;
        }
    }

}
