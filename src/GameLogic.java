import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    Music music = new Music();

    public GameLogic(){
        scan = new Scanner(System.in);
        panel = new PanelSetUp(this);
        timer = new Timer(1000, this);
        timer.start();
    }

    public void start(){
        createPlayer();
        game = new OutputWindow("Teris", this, this.panel);
    }

    public int getTime() {
        return time;
    }

    public void resetTimer() {
        timer.restart();
    }

    private void createPlayer(){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        panel.updateTimer();
    }
}
