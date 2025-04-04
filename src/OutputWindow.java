import java.awt.Color;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class OutputWindow {


    private Timer timer;

    public OutputWindow(String name, GameLogic logic, PanelSetUp panel) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // causes program to end when window is X'd out
        frame.setSize(2000, 1000); // window size
        frame.setLocation(0, 0); // where on screen window appears
        frame.add(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true); // display the frame on screen
    }

}