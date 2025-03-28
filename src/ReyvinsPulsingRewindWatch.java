public class ReyvinsPulsingRewindWatch extends Equipment{
    private int rewind;
    public ReyvinsPulsingRewindWatch(String name, int score, int uses, int rewind){
        super(name, score, uses);
        this.rewind = rewind;
    }

    public int getRewind() {
        return rewind;
    }
}
