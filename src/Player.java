
public class Player {
    private int score;
    private String name;

    public Player(String name, int score) {
        this.name = name;
        score = 0;

    }

    public int getScore(){
        return score;
    }

    public String getName(){
        return name;
    }

    public void addScore(int newPoints){
        score += newPoints;
    }

    public void setScore(int newScore){
        score = newScore;
    }
}
