
public class Player {
    private int score;
    private String name;
    private Equipment[] inventory;

    public Player(String name, int score) {
        this.name = name;
        score = 0;
        inventory = new Equipment[3];
    }

    public Equipment[] getInventory() {
        return inventory;
    }

    public void addToInventory(Equipment e){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null){
                inventory[i] = e;
                break;
            }
        }
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
