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
            blockSprites = new Image[]{new ImageIcon("Visuals/Yellow Block.png").getImage()};
            shapeMatrices.add(new int[][]{
                {1, 1},
                {1, 1}
            });
            canRotate = false;
        } else if (blockType.equals("B")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/BlueBlock(1).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(2).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(3).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(4).png").getImage()
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
                    new ImageIcon("Visuals/CyanBlock(1).png").getImage(),
                    new ImageIcon("Visuals/CyanBlock(2).png").getImage()
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
                    new ImageIcon("Visuals/GreenBlock(1).png").getImage(),
                    new ImageIcon("Visuals/GreenBlock(2).png").getImage()
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
                    new ImageIcon("Visuals/Orange Block(1).png").getImage(),
                    new ImageIcon("Visuals/Orange Block(2).png").getImage()
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
                    new ImageIcon("Visuals/PurpleBlock(1).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(2).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(3).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(4).png").getImage()
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
                    new ImageIcon("Visuals/Red Block(1).png").getImage(),
                    new ImageIcon("Visuals/Red Block(2).png").getImage(),
                    new ImageIcon("Visuals/Red Block(3).png").getImage(),
                    new ImageIcon("Visuals/Red Block(4).png").getImage()
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

    public void moveDown() { y += 33; }
    public void moveLeft() { x -= 33; }
    public void moveRight() { x += 33; }

    public Image getImage() {
        return blockSprites[currentRotation];
    }

    public int[][] getShapeMatrix() {
        return shapeMatrices.get(currentRotation);
    }
}
