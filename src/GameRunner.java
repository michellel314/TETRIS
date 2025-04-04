import java.io.IOException;

public class GameRunner {

    public static void main(String[] args) throws IOException {
        PanelSetUp panel = new PanelSetUp();
        GameLogic game = new GameLogic(panel);
        game.initialize();
        game.start();
    }
}
