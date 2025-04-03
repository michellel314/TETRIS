import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Block {
    private String color;
    private Image[] blockSprites;

    private int currentRotation = 0;
    private Image image;
    private int x, y;
    private ArrayList<int[][]> rotations;
    private int rotationIndex;
    private boolean canRotate;
    public Block(String blockType) {
        if(blockType.equals("A")){
            blockSprites = new Image[]{new ImageIcon("Yellow Block.png").getImage()};
            canRotate = false;
        } else if (blockType.equals("B")) {
            blockSprites = new Image[]{
                    new ImageIcon("BlueBlock(1).png").getImage(),
                    new ImageIcon("BlueBlock(2).png").getImage(),
                    new ImageIcon("BlueBlock(3).png").getImage(),
                    new ImageIcon("BlueBlock(4).png").getImage(),
            };
        } else if (blockType.equals("C")) {
            blockSprites = new Image[]{
                    new ImageIcon("CyanBlock(1).png").getImage(),
                    new ImageIcon("CyanBlock(2).png").getImage()
            };
        } else if (blockType.equals("D")) {
            blockSprites = new Image[]{
                    new ImageIcon("GreenBlock(1).png").getImage(),
                    new ImageIcon("GreenBlock(2).png").getImage()
            };
        } else if (blockType.equals("E")) {
            blockSprites = new Image[]{
                    new ImageIcon("Orange Block(1).png").getImage(),
                    new ImageIcon("Orange Block(2).png").getImage()
            };
        } else if (blockType.equals("F")) {
            blockSprites = new Image[]{
                    new ImageIcon("PurpleBlock(1).png").getImage(),
                    new ImageIcon("PurpleBlock(2).png").getImage(),
                    new ImageIcon("PurpleBlock(3).png").getImage(),
                    new ImageIcon("PurpleBlock(4).png").getImage()
            };
        } else if (blockType.equals("G")){
            blockSprites = new Image[]{
                    new ImageIcon("Red Block(1).png").getImage(),
                    new ImageIcon("Red Block(2).png").getImage(),
                    new ImageIcon("Red Block(3).png").getImage(),
                    new ImageIcon("Red Block(4).png").getImage()
            };
            canRotate = true;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getShape() {
        return rotations.get(rotationIndex);
    }

    public void rotateClockwise() {
        if(canRotate){
            currentRotation = (currentRotation + 1) % blockSprites.length;
        }
    }

    public void rotateCounterClockwise(){
       if(canRotate){
           currentRotation = (currentRotation - 1 + blockSprites.length) % blockSprites.length;
       }
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public Image getImage(){
        if(color.equals("blue")) {
           return blockSprites [currentRotation];
        } else if (color.equals("cyan")){
            return blockSprites[currentRotation];
        } else if (color.equals("green")){
            return blockSprites[currentRotation];
        } else if (color.equals("orange")){
            return blockSprites[currentRotation];
        } else if (color.equals("purple")){
            return blockSprites[currentRotation];
        } else if (color.equals("red")) {
            return blockSprites[currentRotation];
        }
        return blockSprites[0];
    }
}