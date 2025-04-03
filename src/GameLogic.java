import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;

public class GameLogic implements ActionListener {
    private Block currentBlock;
    private final int BLOCK_SIZE = 25;
    private final int PANEL_HEIGHT = 800;
    private Player player;
    private Scanner scan;
    OutputWindow game;
    Timer timer;
    private Timer blockTimer;
    private int time = 0;
    PanelSetUp panel;
    PanelSetUp panel2;
    Shop shop;
    Music music;
    private String type;
    BossFight zaif;
    public int score;

    public GameLogic(PanelSetUp panel) throws IOException {
        scan = new Scanner(System.in);
        zaif = new BossFight(this.panel);
        this.panel = panel;
        panel2 = new PanelSetUp(this, zaif);
        timer = new Timer(1000, this);
        music = new Music("sounds\\MAINSONG.wav");
        blockTimer = new Timer(500, this);
        type = generateBlock();
        currentBlock = new Block(type);
    }

    public void start() {
        createPlayer();
        timer.start();
        blockTimer.start();
        music.setFile();
        game = new OutputWindow("Teris", this, this.panel);
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
            moveBlockDown();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void moveBlockDown(){
        if (currentBlock.getY() + BLOCK_SIZE < PANEL_HEIGHT){
            currentBlock.moveDown();
        } else {
            currentBlock = new Block(type);
        }
        panel.repaint();
    }

    public void moveBlockLeft(){
        currentBlock.moveLeft();
        panel.repaint();
    }

    public void moveBlockRight(){
        currentBlock.moveRight();
        panel.repaint();
    }

    public void rotateBlock(){
        currentBlock.rotateClockwise();
        panel.repaint();
    }

    public Block getCurrentBlock(){
        return currentBlock;
    }

    private String generateBlock(){
        int num = (int) (Math.random() * 6) + 1;
        if(num == 1){
            return "A";
        } else if (num == 2){
            return "B";
        } else if (num == 3){
            return "C";
        } else if (num == 4){
            return "D";
        } else if (num == 5){
            return "E";
        } else if (num == 6){
            return "F";
        } else {
            return "G";
        }
    }


}
