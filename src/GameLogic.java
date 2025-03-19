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

    private void createPlayer(){
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);
    }

    private void setUpPanel(){
        panel = new Block[]
    }




}
