import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.util.ArrayList;
public class Block {
    private Image image;
    private int x;
    private int y;
    private ArrayList<int[][]> rotations;
    private int rotationIndex;
    public Block(Image image, int x, int y, ArrayList<int[][]> rotations){
        this.image = image;
        this.x = x;
        this.y = y;
        this.rotations = rotations;
        this.rotationIndex = 0;
    }

    public Image getImage(){
        return image;
    }
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int[][] getShape(){
        return rotations.get(rotationIndex);
    }
    public void rotate(){
        rotationIndex = (rotationIndex + 1) % rotations.size();
    }
    public void moveDown(){
        y++;
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }
    public void draw(Graphics g){
        g.drawImage(image, x * 30, y * 30, null);
    }
}
