public class ReyvinsPulsingRewindWatch extends Equipment{
    private int extraTurn;
    public ReyvinsPulsingRewindWatch(String name, int score, int uses, int extraTurn){
        super(name, score, uses);
        this.extraTurn = extraTurn;
    }

    public int getExtraTurn() {
        return extraTurn;
    }
}
