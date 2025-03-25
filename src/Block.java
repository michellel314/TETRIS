import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block {

    public static BufferedImage blueBlock1;
    public static BufferedImage blueBlock2;
    public static BufferedImage blueBlock3;
    public static BufferedImage blueBlock4;
    public static BufferedImage cyanBlock1;
    public static BufferedImage cyanBlock2;
    public static BufferedImage greenBlock1;
    public static BufferedImage greenBlock2;
    public static BufferedImage orangeBlock1;
    public static BufferedImage orangeBlock2;
    public static BufferedImage purpleBlock1;
    public static BufferedImage purpleBlock2;
    public static BufferedImage purpleBlock3;
    public static BufferedImage purpleBlock4;
    public static BufferedImage redBlock1;
    public static BufferedImage redBlock2;
    public static BufferedImage redBlock3;
    public static BufferedImage redBlock4;
    public static BufferedImage yellowBlock1;

    public void makeBlocks(){
        try {
            blueBlock1 = ImageIO.read(new File("Visuals\\BlueBlock(1).png"));
            blueBlock2 = ImageIO.read(new File("Visuals\\BlueBlock(2).png"));
            blueBlock3 = ImageIO.read(new File("Visuals\\BlueBlock(3).png"));
            blueBlock4 = ImageIO.read(new File("Visuals\\BlueBlock(4).png"));
            cyanBlock1 = ImageIO.read(new File("Visuals\\CyanBlock(1).png"));
            cyanBlock2 = ImageIO.read(new File("Visuals\\CyanBlock(2).png"));
            greenBlock1 = ImageIO.read(new File("Visuals\\GreenBlock(1).png"));
            greenBlock2 = ImageIO.read(new File("Visuals\\GreenBlock(2).png"));
            orangeBlock1 = ImageIO.read(new File("Visuals\\OrangeBlock(1).png"));
            orangeBlock2 = ImageIO.read(new File("Visuals\\OrangeBlock(2).png"));
            purpleBlock1 = ImageIO.read(new File("Visuals\\PurpleBlock(1).png"));
            purpleBlock2 = ImageIO.read(new File("Visuals\\PurpleBlock(2).png"));
            purpleBlock3 = ImageIO.read(new File("Visuals\\PurpleBlock(3).png"));
            purpleBlock4 = ImageIO.read(new File("Visuals\\PurpleBlock(4).png"));
            redBlock1 = ImageIO.read(new File("Visuals\\RedBlock(1).png"));
            redBlock2 = ImageIO.read(new File("Visuals\\RedBlock(2).png"));
            redBlock3 = ImageIO.read(new File("Visuals\\RedBlock(3).png"));
            redBlock4 = ImageIO.read(new File("Visuals\\RedBlock(4).png"));
            yellowBlock1 = ImageIO.read(new File("Visuals\\YellowBlock.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
