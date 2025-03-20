import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
public class GameLogic {
    private Block[][] panel;
    private Player player;
    private Scanner scan;
    private int x;
    private int y;

    public GameLogic(){
        scan = new Scanner(System.in);
        createPlayer();
    }

    public void start(){
        seterUpPanel();
        createPlayer();
    }

    private void createPlayer(){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);
    }

    private void setUpPanel(){
        panel = new Block[9][17];
        for(int r = 1; r < panel.length; r++){
            for(int c = 1; c < panel[0].length; c++){
                System.out.print("□");
            }
            System.out.println();
        }
//        for(int r = 0; r < 1; r++){
//            if()
        }

    private void seterUpPanel() {
        System.out.print("How big do you want the game? (X): ");
        x = scan.nextInt();
        System.out.print("How big do you want the game? (Y): ");
        y = scan.nextInt();

    }

}
