import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class OutputWindow {

    public OutputWindow(String name) {
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // causes program to end when window is X'd out
        frame.setSize(1000, 1000); // window size
        frame.setLocation(300, 30); // where on screen window appears
        PanelSetUp panel = new PanelSetUp();
        frame.add(panel);
        frame.setVisible(true); // display the frame on screen
    }

}