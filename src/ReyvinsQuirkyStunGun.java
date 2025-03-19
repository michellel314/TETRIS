public class ReyvinsQuirkyStunGun extends Equipment{
    private int pauseDuration;
    public ReyvinsQuirkyStunGun (String name, int score, int uses, int pauseDuration){
        super(name, score, uses);
        this.pauseDuration = pauseDuration;
    }

    public int getPauseDuration() {
        return pauseDuration;
    }
}
