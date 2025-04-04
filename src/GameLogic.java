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
    PanelSetUp panel2;
    Shop shop;
    Music music;
    BossFight zaif;
    public static final int WIDTH = 9;
    private static final int HEIGHT = 17;
    int[][] grid = new int[HEIGHT][WIDTH];
    private Block currentBlock;
    private int score;

    public GameLogic() throws IOException {
        zaif = new BossFight(this.panel);
        shop = new Shop();
        panel = new PanelSetUp(this, zaif, this.shop);
        scan = new Scanner(System.in);
        timer = new Timer(1000, this);
        music = new Music("sounds\\MAINSONG.wav");

        for(int i = 0; i < HEIGHT; i++){
            for(int j = 0; j < WIDTH; j++){
                grid[i][j] = 0;
            }
        }
    }
    public void spawnBlock(){
       String blockType = panel.generateBlock();
       currentBlock = new Block(blockType);
    }

    public boolean moveBlockDown(){
        int newY = currentBlock.getY() + 25;
        if (isCollision(newY)) {
            placeBlock();
            return false;  // Block has landed, stop moving down
        }
        currentBlock.moveDown();
        return true;  // Block successfully moved down
    }

    public void moveBlockLeft(){
        if(!isLeftCollision()){
            currentBlock.setX(currentBlock.getX() - 25);
        }
    }

    public void moveBlockRight(){
        if(!isRightCollision()){
            currentBlock.setX(currentBlock.getX() + 25);
        }
    }

    public void rotateClockwise(){
        currentBlock.rotateClockwise();
        if(isCollision(currentBlock.getY())){
            currentBlock.rotateCounterClockwise();
        }
    }


    public void start() {
        createPlayer();
        timer.start();
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

    public boolean isGameOver() {
        for (int i = 0; i < WIDTH; i++) {
            if (grid[0][i] != 0) {
                return true;  // Game over if the top row is filled
            }
        }
        return false;
    }

    private void createPlayer() {
        System.out.print("Enter your name: ");
        String name = scan.nextLine();
        player = new Player(name, 0);

        // Debugging: Player creation
        System.out.println("Player created with name: " + name);
    }

    public void startBossFight() throws IOException {
        panel.bossFightStarted = true;
        zaif.start();
    }

    public void openShop(){
        panel.gameRunning = false;
        panel.isShopOpen = true;
        shop.openShop();
    }

    public void closeShop(){
        panel.isShopOpen = false;
        panel.gameRunning = true;
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

    private boolean isCollision(int newY){
        if(newY >= HEIGHT * 25){
            return true;
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(currentBlock.getImage() != null && grid[newY / 25 + i][currentBlock.getX() / 25 + j] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private void placeBlock(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currentBlock.getImage() != null) {
                    grid[currentBlock.getY() / 25 + i][currentBlock.getX() / 25 + j] = 1; // Place the block
                }
            }
        }
        clearRows();  // After placing, check for filled rows
        spawnBlock();  // Spawn a new block
    }

    private void clearRows(){
        for (int i = 0; i < HEIGHT; i++) {
            boolean isRowFull = true;
            for (int j = 0; j < WIDTH; j++) {
                if (grid[i][j] == 0) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                clearRow(i);  // Clear the row if it's full
            }
        }
    }

    private void clearRow(int row){
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = grid[i - 1][j];
            }
        }
        // Clear the top row after shifting
        for (int j = 0; j < WIDTH; j++) {
            grid[0][j] = 0;
        }
        score += 100;  // Increase score for clearing a row
    }

    private boolean isLeftCollision(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(currentBlock.getImage() != null && grid[(currentBlock.getY() + i * 25) / 25][(currentBlock.getX() + j * 25 - 25) / 25] != 0){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isRightCollision(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(currentBlock.getImage() != null && grid[(currentBlock.getY() + i * 25) / 25][(currentBlock.getX() + j * 25 + 25) / 25] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    public int getScore(){
        return score;
    }

    public Block getCurrentBlock(){
        return currentBlock;
    }
}
