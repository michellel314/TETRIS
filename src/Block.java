import java.awt.*;
import javax.swing.*;

public class Block {
    private Image[] blockSprites;
    private int currentRotation = 0;
    private int x;
    private int y;
    private boolean canRotate;

    public Block(String blockType) {
        this.x = 650;
        this.y = 20;
        if (blockType.equals("A")) {
            blockSprites = new Image[]{new ImageIcon("Visuals/Yellow Block.png").getImage()};
            canRotate = false;
        } else if (blockType.equals("B")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/BlueBlock(1).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(2).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(3).png").getImage(),
                    new ImageIcon("Visuals/BlueBlock(4).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("C")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/CyanBlock(1).png").getImage(),
                    new ImageIcon("Visuals/CyanBlock(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("D")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/GreenBlock(1).png").getImage(),
                    new ImageIcon("Visuals/GreenBlock(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("E")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/Orange Block(1).png").getImage(),
                    new ImageIcon("Visuals/Orange Block(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("F")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/PurpleBlock(1).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(2).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(3).png").getImage(),
                    new ImageIcon("Visuals/PurpleBlock(4).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("G")) {
            blockSprites = new Image[]{
                    new ImageIcon("Visuals/Red Block(1).png").getImage(),
                    new ImageIcon("Visuals/Red Block(2).png").getImage(),
                    new ImageIcon("Visuals/Red Block(3).png").getImage(),
                    new ImageIcon("Visuals/Red Block(4).png").getImage()
            };
            canRotate = true;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int newX) { x = newX; }
    public void setY(int newY) { y = newY; }

    public void rotateClockwise() {
        if (canRotate) {
            currentRotation = (currentRotation + 1) % blockSprites.length;
        }
    }

    public void rotateCounterClockwise() {
        if (canRotate) {
            currentRotation = (currentRotation - 1 + blockSprites.length) % blockSprites.length;
        }
    }

    public void moveDown() { y += 5; }
    public void moveLeft() { x -= 5; }
    public void moveRight() { x += 5; }

    public Image getImage() {
        return blockSprites[currentRotation];
    }
}
