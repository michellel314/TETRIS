import java.awt.*;
import java.lang.reflect.Array;

public class Colors {
    public static final String RED = "#FF0000";     // RED

    public static final String GREEN = "#00FF00";   // GREEN

    public static final String YELLOW = "#FFFF00";  // YELLOW

    public static final String BLUE = "#0000FF";    // BLUE

    public static final String PURPLE = "#800080";  // PURPLE

    public static final String CYAN = "#00FFFF";// CYAN

    public static final String ORANGE = "#FFA500";

    public static Color randomColor(){
        Color[] colors = new Color[]{Color.decode(Colors.RED), Color.decode(Colors.GREEN), Color.decode(Colors.YELLOW), Color.decode(Colors.BLUE), Color.decode(Colors.PURPLE), Color.decode(Colors.CYAN), Color.decode(Colors.ORANGE)};
        return colors[(int) (Math.random() * 7)];
    }

}
