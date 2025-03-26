import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
import java.util.TimerTask;


public class GameLogic implements ActionListener {
    private Block[][] panel;
    private Player player;
    private Scanner scan;
    OutputWindow game;
    Timer timer;


    public GameLogic(){
        scan = new Scanner(System.in);
        timer = new Timer();
    }

    public void start(){
        createPlayer();
        game = new OutputWindow("Teris", this.timer);
    }

    private void createPlayer(){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
