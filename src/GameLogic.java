import java.util.ArrayList;
import java.util.Scanner;
public class GameLogic {
    private Block[][] panel;
    private Player player;
    private Scanner scan;

    public GameLogic(){
        scan = new Scanner(System.in);
        createPlayer();
    }

    public void start(){
        setUpPanel();
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


    }




}
