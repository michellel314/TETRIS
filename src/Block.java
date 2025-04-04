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
    private int x;
    private int y;
    private boolean canRotate;
    public Block(String blockType) {
        this.x = 325;
        this.y = 45;
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
            canRotate = true;
        } else if (blockType.equals("C")) {
            blockSprites = new Image[]{
                    new ImageIcon("CyanBlock(1).png").getImage(),
                    new ImageIcon("CyanBlock(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("D")) {
            blockSprites = new Image[]{
                    new ImageIcon("GreenBlock(1).png").getImage(),
                    new ImageIcon("GreenBlock(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("E")) {
            blockSprites = new Image[]{
                    new ImageIcon("Orange Block(1).png").getImage(),
                    new ImageIcon("Orange Block(2).png").getImage()
            };
            canRotate = true;
        } else if (blockType.equals("F")) {
            blockSprites = new Image[]{
                    new ImageIcon("PurpleBlock(1).png").getImage(),
                    new ImageIcon("PurpleBlock(2).png").getImage(),
                    new ImageIcon("PurpleBlock(3).png").getImage(),
                    new ImageIcon("PurpleBlock(4).png").getImage()
            };
            canRotate = true;
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

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
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
        y += 25;
    }

    public void moveLeft() {
        x -= 25;
    }

    public void moveRight() {
        x += 25;
    }

    public Image getImage(){
        return blockSprites[currentRotation];
    }
}