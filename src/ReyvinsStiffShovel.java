public class ReyvinsStiffShovel extends Equipment{
    private int numberOfRows;
    public ReyvinsStiffShovel(String name, int score, int uses, int numberOfRows){
        super(name, score, uses);
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
