import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;

public class GameLogic implements ActionListener {
    private Player player;
    private Scanner scan;
    OutputWindow game;
    Timer timer;
    private int time = 0;
    PanelSetUp panel;
    Shop shop;
    Music music;
    BossFight zaif;
    public int score;

    public GameLogic() throws IOException {
        scan = new Scanner(System.in);
        zaif = new BossFight(this.panel);
        this.panel = panel;
        panel2 = new PanelSetUp(this, zaif);
        shop = new Shop();
        panel = new PanelSetUp(this, zaif, this.shop);
        timer = new Timer(1000, this);
        music = new Music("sounds\\MAINSONG.wav");
    }

    public void start() {
        createPlayer();
        timer.start();
        music.setFile();
        game = new OutputWindow("Teris", this, this.panel);
        panel.loadBlockImages();
        // Debugging: Confirm game start
        System.out.println("Game started for player: " + player.getName());
    }

    public int getTime() {
        return time;
    }

    public void resetTimer() {
        timer.restart();
    }

    private void createPlayer() {
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);

        // Debugging: Player creation
        System.out.println("Player created with name: " + name);
    }

    public void startBossFight() throws IOException {
        zaif.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        try {
            panel.updateTimer();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
