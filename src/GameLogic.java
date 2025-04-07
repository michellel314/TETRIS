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
    public static final int WIDTH = 9;
    public static final int HEIGHT = 17;
    public int[][] grid = new int[HEIGHT][WIDTH];
    private Block currentBlock;
    private int score;
    PanelSetUp panel;
    Shop shop;
    Music music;
    BossFight zaif;

    public GameLogic() throws IOException {
        spawnBlock();
        shop = new Shop(player);
        panel = new PanelSetUp(this, shop);
        scan = new Scanner(System.in);
        timer = new Timer(1000, this);
        music = new Music("sounds/MAINSONG.wav");

        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                grid[i][j] = 0;
    }

    public void start() {
        createPlayer();
        timer.start();
        music.setFile();
        game = new OutputWindow("Tetris", this, panel);
    }

    private void createPlayer() {
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);
    }

    public void spawnBlock() {
        currentBlock = new Block(generateBlock());
    }

    public String generateBlock() {
        int num = (int) (Math.random() * 7) + 1;
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

    public boolean moveBlockDown() {
      //  int newY = currentBlock.getY() + 25;
     //   if (newY >= HEIGHT * 25 || grid[newY / 25][currentBlock.getX() / 25] != 0) {
      ///      placeBlock();
     //       return false;
     //   }
      currentBlock.moveDown();
       return true;
    }

    private void placeBlock() {
        int row = currentBlock.getY() / 25;
        int col = currentBlock.getX() / 25;
        if (row < HEIGHT && col < WIDTH)
            grid[row][col] = 1;

        clearRows();
        spawnBlock();
    }

    private void clearRows() {
        for (int i = 0; i < HEIGHT; i++) {
            boolean full = true;
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) clearRow(i);
        }
    }

    private void clearRow(int row) {
        for (int i = row; i > 0; i--)
            System.arraycopy(grid[i - 1], 0, grid[i], 0, WIDTH);
        for (int j = 0; j < WIDTH; j++)
            grid[0][j] = 0;
        score += 100;
    }

    public int getTime() { return time; }
    public int getScore() { return score; }
    public Block getCurrentBlock() { return currentBlock; }


    public void openShop() {
        panel.gameRunning = false;
        panel.isShopOpen = true;
        shop.openShop();
    }

    public void closeShop() {
        panel.isShopOpen = false;
        panel.gameRunning = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
        try {
            panel.updateTimer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
