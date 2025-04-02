import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Block {
    private String color;
    private Image[] blockSprites;
    private Image[] blueBlockSprites;
    private Image[] cyanBlockSprites;
    private Image[] greenBlockSprites;
    private Image[] orangeBlockSprites;
    private Image[] purpleBlockSprites;
    private Image[] redBlockSprites;
    private int currentRotation = 0;
    private Image image;
    private int x, y;
    private ArrayList<int[][]> rotations;
    private int rotationIndex;
    private boolean canRotate;
    public Block(String blockType) {
        if(blockType.equals("0")){
            blockSprites = new Image[]{new ImageIcon("Yellow Block.png").getImage()};
            canRotate = false;
        } else {
            blueBlockSprites = new Image[]{
                    new ImageIcon("BlueBlock(1).png").getImage(),
                    new ImageIcon("BlueBlock(2).png").getImage(),
                    new ImageIcon("BlueBlock(3).png").getImage(),
                    new ImageIcon("BlueBlock(4).png").getImage(),
            };

            cyanBlockSprites = new Image[]{
                    new ImageIcon("CyanBlock(1).png").getImage(),
                    new ImageIcon("CyanBlock(2).png").getImage()
            };

            greenBlockSprites = new Image []{
                    new ImageIcon("GreenBlock(1).png").getImage(),
                    new ImageIcon("GreenBlock(2).png").getImage()
            };

            orangeBlockSprites = new Image[]{
                    new ImageIcon("Orange Block(1).png").getImage(),
                    new ImageIcon("Orange Block(2).png").getImage()
            };

            purpleBlockSprites = new Image[]{
                    new ImageIcon("PurpleBlock(1).png").getImage(),
                    new ImageIcon("PurpleBlock(2).png").getImage(),
                    new ImageIcon("PurpleBlock(3).png").getImage(),
                    new ImageIcon("PurpleBlock(4).png").getImage()
            };

            redBlockSprites = new Image[]{
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
            if(color.equals("blue")) {
                currentRotation = (currentRotation + 1) % blueBlockSprites.length;
            } else if (color.equals("cyan")){
                currentRotation = (currentRotation + 1) % cyanBlockSprites.length;
            } else if (color.equals("green")){
                currentRotation = (currentRotation + 1) % greenBlockSprites.length;
            } else if (color.equals("orange")){
                currentRotation = (currentRotation + 1) % orangeBlockSprites.length;
            } else if (color.equals("purple")){
                currentRotation = (currentRotation + 1) % purpleBlockSprites.length;
            } else if (color.equals("red")) {
                currentRotation = (currentRotation + 1) % redBlockSprites.length;
            }
        }
    }

    public void rotateCounterClockwise(){
        if(canRotate){
            if(color.equals("blue")) {
                currentRotation = (currentRotation - 1 + blueBlockSprites.length) % blueBlockSprites.length;
            } else if (color.equals("cyan")){
                currentRotation = (currentRotation - 1 + cyanBlockSprites.length) % cyanBlockSprites.length;
            } else if (color.equals("green")){
                currentRotation = (currentRotation - 1 + greenBlockSprites.length) % greenBlockSprites.length;
            } else if (color.equals("orange")){
                currentRotation = (currentRotation - 1 + orangeBlockSprites.length) % orangeBlockSprites.length;
            } else if (color.equals("purple")){
                currentRotation = (currentRotation - 1 + purpleBlockSprites.length) % purpleBlockSprites.length;
            } else if (color.equals("red")) {
                currentRotation = (currentRotation - 1 + redBlockSprites.length) % redBlockSprites.length;
            }
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

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x * 30, y * 30, 30, 30, null);
        }
    }


    private String generateColor(){
        int pos = (int)(Math.random() * 6) + 1;
        if (pos == 1){
            color = "blue";
        } else if (pos == 2){
            color = "cyan";
        } else if (pos == 3){
            color = "green";
        } else if (pos == 4){
            color = "orange";
        } else if (pos == 5){
            color = "purple";
        } else if (pos == 6){
            color = "red";
        } else {
            color = "yellow";
        }
        return color;
    }

    public Image getImage(){
        if(color.equals("blue")) {
           return blueBlockSprites[currentRotation];
        } else if (color.equals("cyan")){
            return cyanBlockSprites[currentRotation];
        } else if (color.equals("green")){
            return greenBlockSprites[currentRotation];
        } else if (color.equals("orange")){
            return orangeBlockSprites[currentRotation];
        } else if (color.equals("purple")){
            return purpleBlockSprites[currentRotation];
        } else if (color.equals("red")) {
            return redBlockSprites[currentRotation];
        }
        return blockSprites[0];
    }
}