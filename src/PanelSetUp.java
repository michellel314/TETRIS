import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelSetUp extends JPanel implements KeyListener, ActionListener , MouseListener{
    public Timer blockTimer;
    private BufferedImage grid, title;
    private StyledDocument doc;
    private Style style;
    private JTextPane textPane;
    public GameLogic logic;
    private OutputWindow game;
    private BossFight zaif;
    private Shop shop;
    public boolean bossFightStarted;
    public Boolean isShopOpen;
    public Boolean gameRunning;
    private int[] xs;
    private int[] ys;
    public int blockFallSpeed = 500;
    public Button button;
    public Button buyGun;
    public Button buyWatch;
    public Button buyShovel;
    public Button buyHeels;
    public Button endGame;
    public boolean gunClicked;
    public boolean watchClicked;
    public boolean heelsClicked;
    public boolean shovelClicked;
    public boolean equipmentGoBoom;
    private boolean gameEnd;

    public PanelSetUp(GameLogic logic, Shop shop) throws IOException{
        this.logic = logic;
        this.zaif = new BossFight(this);
        this.shop = shop;
        setFocusable(true);
        addKeyListener(this);
        setupTextPane();
        setupImages();
        gameRunning = true;
        isShopOpen = false;
        bossFightStarted = false;
        gunClicked = false;
        watchClicked = false;
        heelsClicked = false;
        shovelClicked = false;
        equipmentGoBoom = false;
        gameEnd = false;
        button = new Button("Close Shop");
        button.addActionListener(shop.button);
        add(button);
        buyGun = new Button("Buy Reyvin's Quirky Stun Gun");
        buyGun.addActionListener(shop.gun.button);
        add(buyGun);
        buyHeels = new Button("Buy Reyvin's Girly-Pop High Heels");
        buyHeels.addActionListener(shop.highheels.button);
        add(buyHeels);
        buyShovel = new Button("Buy Reyvin's Stiff Shovel");
        buyShovel.addActionListener(shop.shovel.button);
        add(buyShovel);
        buyWatch = new Button("Buy Reyvin's Pulsing Rewind Watch");
        buyWatch.addActionListener(shop.watch.button);
        add(buyWatch);
        endGame = new Button("OK");
        endGame.addActionListener(this);
        add(endGame);

        blockTimer = new Timer(blockFallSpeed, e -> {
            boolean moving = logic.moveBlockDown();
            if (!moving) logic.spawnBlock();
            repaint();
        });
        blockTimer.start();
    }

    public void updateTimer() throws IOException {
        repaint();
            for (int j = 0; j < WIDTH; j++)
                if (logic.grid[0][j] != 0){
                    gameRunning = false;
                    gameEnd = true;
                    break;
                }

        if (gameEnd) {
            endGame.setVisible(true);
            zaif.endMusic();
            blockTimer.stop();
        } else {
            endGame.setVisible(false);
        }

        if ((Math.random() * 10) + 1 == 7){
            equipmentGoBoom = true;
        }
        if (!isShopOpen){
            button.setVisible(false);
            buyWatch.setVisible(false);
            buyShovel.setVisible(false);
            buyGun.setVisible(false);
            buyHeels.setVisible(false);
        } else {
            buyWatch.setVisible(true);
            buyShovel.setVisible(true);
            buyGun.setVisible(true);
            buyHeels.setVisible(true);
            button.setVisible(true);
        }
        if (logic.getScore() == 500) {
            logic.openShop();
        }
        if (logic.getScore() == 1000) {
            logic.music.stop();
            bossFightStarted = true;
            zaif.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Score: " + logic.getScore(), 10, 10);
        g.drawString(String.valueOf(logic.getTime()), 10, 1000);

        // Draw placed blocks
        for (int row = 0; row < GameLogic.HEIGHT; row++) {
            for (int col = 0; col < GameLogic.WIDTH; col++) {
                if (logic.grid[row][col] == 1) {
                    g.setColor(Color.decode(Colors.BLUE));
                } else if (logic.grid[row][col] == 2){
                    g.setColor(Color.decode(Colors.CYAN));
                } else if(logic.grid[row][col] == 3){
                    g.setColor(Color.decode(Colors.GREEN));
                } else if(logic.grid[row][col] == 4){
                    g.setColor(Color.decode(Colors.ORANGE));
                } else if (logic.grid[row][col] == 5){
                    g.setColor(Color.decode(Colors.PURPLE));
                } else if(logic.grid[row][col] == 6){
                    g.setColor(Color.decode(Colors.RED));
                    g.fillRect(650 + col * 33, 20 + row * 33, 33, 33);
                } else if(logic.grid[row][col] == 7){
                    g.setColor(Color.decode(Colors.YELLOW));
                }

                if(logic.grid[row][col] != 0){
                    g.fillRect(650 + col * 33, 20 + row * 33, 33, 33);
                }

            }
        }

        if (equipmentGoBoom){
            if (logic.player.pickRandomFromInventory().equipmentUsed() != null){
                g.drawString(logic.player.pickRandomFromInventory().equipmentUsed(), 1800, 1800);
            }
        }

        Block current = logic.getCurrentBlock();
        if (current != null) {
            g.drawImage(current.getImage(), current.getX(), current.getY(), null);
        }

        if (bossFightStarted) {
            g.drawImage(zaif.getZaif(), 1500, 210, null);
            g.setColor(Colors.randomColor());
            g.drawLine(1596, 402, 1596, 800);
            g.drawLine(1596, 800, 1496, 950);
            g.drawLine(1596, 800, 1696, 950);
            g.drawLine(1500, 402, 1400, 600);
            g.drawLine(1692, 402, 1800, 600);
            g.setFont(new Font("Times New Roman", Font.BOLD, 100));
            g.drawString("Boss: Z.A.I.F.", 1300, 110);
        }

        if (isShopOpen) {
            xs = new int[]{1100, 1050, 1000};
            ys = new int[]{300, 300, 380};
            g.drawImage(shop.getShop(), 200, 0, null);
            g.drawImage(shop.getReyvin(), 700, 300, null);
            g.setColor(Color.white);
            g.drawOval(610, 130, 600, 200);
            g.drawPolygon(xs, ys, 3);
            g.fillPolygon(xs, ys, 3);
            g.fillOval(610, 130, 600, 200);
            g.setFont(new Font("Times New Roman", Font.BOLD, 45));
            g.setColor(Color.magenta);

            if (shop.highheels.getExistsInInv() && shop.shovel.getExistsInInv() && shop.gun.getExistsInInv() && shop.watch.getExistsInInv()) {
                g.drawString("I got nothing to sell you.", 660, 250);
            } else {
                if (!heelsClicked && !watchClicked && !gunClicked && !shovelClicked){
                    g.drawString("Buy something.", 730, 200);
                    g.drawString("Stop wasting my time.", 680, 275);
                }
                if (!shop.highheels.getExistsInInv()) {
                    g.drawImage(shop.highheels.getFile(), 300, 640, null);
                    if (heelsClicked) {
                        if (logic.player.getScore() >= shop.highheels.getScore()){
                            g.drawString("You bought high heels.", 730, 200);
                            shop.buyHeels();
                            heelsClicked = false;
                        } else {
                            g.drawString("You're broke.", 730, 200);
                            heelsClicked = false;
                        }
                    }
                } else {
                    buyHeels.setVisible(false);
                }
                if (!shop.shovel.getExistsInInv()) {
                    g.drawImage(shop.shovel.getFile(), 1000, 700, null);
                    if (shovelClicked) {
                        if (logic.player.getScore() >= shop.shovel.getScore()){
                            g.drawString("You bought a shovel.", 730, 200);
                            shop.buyShovel();
                            shovelClicked = false;
                        } else {
                            g.drawString("You're broke.", 730, 200);
                            shovelClicked = false;
                        }
                    }
                } else {
                    buyShovel.setVisible(false);
                }
                if (!shop.gun.getExistsInInv()) {
                    g.drawImage(shop.gun.getFile(), 600, 700, null);
                    if (gunClicked) {
                        if (logic.player.getScore() >= shop.gun.getScore()){
                            g.drawString("You bought a gun.", 730, 200);
                            shop.buyGun();
                            gunClicked = false;
                        } else {
                            g.drawString("You're broke.", 730, 200);
                            gunClicked = false;
                        }
                    }
                } else {
                    buyGun.setVisible(false);
                }
                if (!shop.watch.getExistsInInv()) {
                    g.drawImage(shop.watch.getFile(), 1200, 600, null);
                    if (watchClicked) {
                        if (logic.player.getScore() >= shop.watch.getScore()){
                            g.drawString("You bought a watch.", 730, 200);
                            shop.buyWatch();
                            watchClicked = false;
                        } else {
                            g.drawString("You're broke.", 730, 200);
                            watchClicked = false;
                        }
                    }
                } else {
                    buyWatch.setVisible(false);
                }
            }
        }

        if (gameRunning) {
            g.drawImage(grid, 650, 20, null);
            g.drawImage(title, 725, 0, null);
        }

        if (gameEnd){
            g.setColor(Color.white);
            g.drawRect(0,0,1300,1000);
            g.fillRect(0,0,1300,1000);

            g.setFont(new Font("Times New Roman", Font.BOLD, 100));
            g.setColor(Colors.randomColor());
            g.drawString("Game Over", 300, 500);
            g.drawString("Final score: " + logic.getScore(), 300, 800);
            endGame.setLocation(1000, 800);
        }

    }


    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameRunning && !isShopOpen) {
            Block current = logic.getCurrentBlock();
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && logic.canMove(-33, 0)) {
                current.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT && logic.canMove(33, 0)) {
                current.moveRight();
            } else if (key == KeyEvent.VK_DOWN) {
                logic.moveBlockDown(); // move down and place if necessary
            } else if (key == KeyEvent.VK_UP) {
                logic.rotateBlock();   // 🔁 use your new method!
            }

            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logic.game.dispose();
    }


    private void setupTextPane() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        doc = textPane.getStyledDocument();
        style = doc.addStyle("my style", null);
        StyleConstants.setFontSize(style, 25);
        add(textPane);
    }

    private void setupImages() {
        try {
            grid = ImageIO.read(getClass().getResource("Visuals/NEW OUTLINE.png"));
            title = ImageIO.read(getClass().getResource("Visuals/title  (1).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
