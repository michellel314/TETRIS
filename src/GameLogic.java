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
    public static final int WIDTH = 16;
    public static final int HEIGHT = 21;
    public int[][] grid = new int[HEIGHT][WIDTH];
    private Block currentBlock;
    private int score;
    PanelSetUp panel;
    Shop shop;
    Music music;
    BossFight zaif;

    public GameLogic() throws IOException {
        shop = new Shop();
        spawnBlock();
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
        if (!collides(currentBlock, currentBlock.getX(), currentBlock.getY() + 33)) {
            currentBlock.moveDown();
            return true;
        } else {
            placeBlock();
            return false;
        }
    }

    public boolean collides(Block block, int newX, int newY) {
        int[][] shape = block.getShapeMatrix();
        int cols = shape[0].length;
        int rows = shape.length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (shape[row][col] == 1) {

                    int gridX = getGridCol(newX) + col;
                    int gridY = getGridRow(newY) + row;

                    if (gridY >= HEIGHT) return true;
                    if (gridY >= 0 && grid[gridY][gridX] != 0) return true;
                }
            }
        }
        return false;
    }

    private void placeBlock() {
        int[][] shape = currentBlock.getShapeMatrix();
        int cols = shape[0].length;
        int rows = shape.length;
        int startX = getGridCol(currentBlock.getX());
        int startY = getGridRow(currentBlock.getY());

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (shape[row][col] == 1) {
                    int gridX = startX + col;
                    int gridY = startY + row;

                    if (gridX >= 0 && gridX < WIDTH && gridY >= 0 && gridY < HEIGHT) {
                        grid[gridY][gridX] = 1;
                    }
                }
            }
        }

        clearRows();
        spawnBlock();
    }

    public boolean canMove(int dx, int dy) {
        int newX = currentBlock.getX() + dx;
        int newY = currentBlock.getY() + dy;

        int col = getGridCol(newX);
        int row = getGridRow(newY);

        return (col >= 0 && col < WIDTH && row >= 0 && row < HEIGHT && grid[row][col] == 0);
    }

    public void rotateBlock() {
        Block preview = new Block(currentBlock); // copy constructor
        preview.rotateClockwise();

        if (canRotateTo(preview.getShapeMatrix())) {
            currentBlock.rotateClockwise();
        }
    }

    public boolean canRotateTo(int[][] newMatrix) {
        int newX = currentBlock.getX();
        int newY = currentBlock.getY();

        for (int row = 0; row < newMatrix.length; row++) {
            for (int col = 0; col < newMatrix[0].length; col++) {
                if (newMatrix[row][col] == 1) {
                    int gridX = getGridCol(newX) + col;
                    int gridY = getGridRow(newY) + row;

                    if (gridX < 0 || gridX >= WIDTH || gridY >= HEIGHT)
                        return false;
                    if (gridY >= 0 && grid[gridY][gridX] != 0)
                        return false;
                }
            }
        }
        return true;
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


    private int getGridRow(int y){
        int row =  (y - 20) / 33;
        return Math.min(row, GameLogic.HEIGHT - 1);

    }


    private int getGridCol(int x){
        return (x - 650) / 33;
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
