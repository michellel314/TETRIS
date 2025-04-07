import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class Block {
    private Image[] blockSprites;
    private ArrayList<int[][]> shapeMatrices;
    private int currentRotation = 0;
    private int x;
    private int y;
    private boolean canRotate;

    public Block(String blockType) {
        this.x = 650;
        this.y = 10;
        shapeMatrices = new ArrayList<>();
        if (blockType.equals("A")) {
            blockSprites = new Image[]{new ImageIcon(getClass().getResource("Visuals/YellowBlock(1).png")).getImage()};
            shapeMatrices.add(new int[][]{
                {1, 1},
                {1, 1}
            });
            canRotate = false;
        } else if (blockType.equals("B")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/BlueBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/BlueBlock(2).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/BlueBlock(3).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/BlueBlock(4).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {1, 0, 0},
                    {1, 1, 1},
            });
            shapeMatrices.add(new int[][]{
                    {0, 1},
                    {0, 1},
                    {1, 1}
            });
            shapeMatrices.add(new int[][]{
                    {1, 1, 1},
                    {0, 0, 1}
            });
            shapeMatrices.add(new int[][]{
                    {1, 1},
                    {1, 0},
                    {1, 0}
            });
            canRotate = true;
        } else if (blockType.equals("C")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/CyanBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/CyanBlock(2).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {1, 1, 1, 1}
            });
            shapeMatrices.add(new int[][]{
                    {1},
                    {1},
                    {1},
                    {1}
            });
            canRotate = true;
        } else if (blockType.equals("D")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/GreenBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/GreenBlock(2).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {0, 1, 1},
                    {1, 1, 0}
            });
            shapeMatrices.add(new int[][]{
                    {1, 0},
                    {1, 1},
                    {0, 1}
            });
            canRotate = true;
        } else if (blockType.equals("E")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/OrangeBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/OrangeBlock(2).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {1, 1, 0},
                    {0, 1, 1}
            });
            shapeMatrices.add(new int[][]{
                    {0, 1},
                    {1, 1},
                    {1, 0}
            });
            canRotate = true;
        } else if (blockType.equals("F")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/PurpleBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/PurpleBlock(2).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/PurpleBlock(3).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/PurpleBlock(4).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {0, 1, 0},
                    {1, 1, 1}
            });
            shapeMatrices.add(new int[][]{
                    {0, 1},
                    {1, 1},
                    {0, 1}
            });
            shapeMatrices.add(new int[][]{
                    {1, 1, 1},
                    {0, 1, 0}
            });
            shapeMatrices.add(new int[][]{
                    {1, 0},
                    {1, 1},
                    {1, 0}
            });
            canRotate = true;
        } else if (blockType.equals("G")) {
            blockSprites = new Image[]{
                    new ImageIcon(getClass().getResource("Visuals/RedBlock(1).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/RedBlock(2).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/RedBlock(3).png")).getImage(),
                    new ImageIcon(getClass().getResource("Visuals/RedBlock(4).png")).getImage()
            };
            shapeMatrices.add(new int[][]{
                    {1, 1},
                    {1, 0},
                    {1, 0}
            });
            shapeMatrices.add(new int[][]{
                    {1, 1, 1},
                    {0, 0, 1}
            });
            shapeMatrices.add(new int[][]{
                    {1, 0, 0},
                    {1, 1, 1}
            });
            shapeMatrices.add(new int[][]{
                    {0, 1},
                    {0, 1},
                    {1, 1}
            });
            canRotate = true;

        }
    }

    public Block(Block other) {
        this.blockSprites = other.blockSprites;
        this.shapeMatrices = other.shapeMatrices;
        this.currentRotation = other.currentRotation;
        this.x = other.x;
        this.y = other.y;
        this.canRotate = other.canRotate;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean getCanRotate(){
        return canRotate;
    }
    public void setX(int newX) { x = newX; }
    public void setY(int newY) { y = newY; }

    public void rotateClockwise() {
        if (canRotate) {
            currentRotation = (currentRotation + 1) % shapeMatrices.size();
        }
    }

    public void rotateCounterClockwise() {
        if (canRotate) {
            currentRotation = (currentRotation - 1 + blockSprites.length) % blockSprites.length;
        }
    }

    public void moveDown() {
        int[][] shape = this.getShapeMatrix();
        int cols = shape[0].length;
        int rows = shape.length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (shape[row][col] == 1) {
                    int gridY = getGridRow(y);
                    if (gridY >= 21){
                        return;
                    }
                }
            }
        }
        y += 33;
    }
    public void moveLeft() {
        int[][] shape = this.getShapeMatrix();
        int cols = shape[0].length;
        int rows = shape.length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (shape[row][col] == 1) {

                    int gridX = getGridCol(x) + col;
                    if (gridX == 15) {
                        return;
                    }

                }
            }
        }
        x -= 33;
    }
    public void moveRight() {
        int[][] shape = this.getShapeMatrix();
        int cols = shape[0].length;
        int rows = shape.length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (shape[row][col] == 1) {

                    int gridX = getGridCol(x) + col;
                    if (gridX == 15) {
                        return;
                    }

                }
            }
        }
        x += 33;
    }

    private int getGridRow(int y){
        int row =  (y - 20) / 33;
        return Math.min(row, GameLogic.HEIGHT - 1);

    }


    private int getGridCol(int x){
        return (x - 650) / 33;
    }

    public Image getImage() {
        return blockSprites[currentRotation];
    }

    public int[][] getShapeMatrix() {
        return shapeMatrices.get(currentRotation);
    }
}
