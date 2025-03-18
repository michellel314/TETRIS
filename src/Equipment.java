public class Equipment{

    private int uses;
    String name;
    int score;

    public Equipment(String name, int score, int uses) {
        this.uses = uses;
        this.name = name;
        this.score = score;
    }

    public int getUses() {
        return uses;
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
