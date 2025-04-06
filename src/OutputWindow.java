import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.*;
public class OutputWindow extends JFrame{


    private Timer timer;

    public OutputWindow(String name, GameLogic logic, PanelSetUp panel) {
        super(name);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // causes program to end when window is X'd out
        setSize(2000, 1000); // window size
        setLocation(0, 0); // where on screen window appears
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true); // display the frame on screen
    }

}