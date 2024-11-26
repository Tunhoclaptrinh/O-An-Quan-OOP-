package GameGUI;

import OCo.OCo;
import OCo.ODan;

import java.awt.*;

public class Chooser {
    public static Color chooserColor = Color.CYAN;
    public static int WIDTH = ODan.WIDTH - 3* OCo.THICKNESS;
    public static int HEIGHT = ODan.HEIGHT - 3*OCo.THICKNESS;
    public static int THICKNESS = 4;
    public static int x = ((Consts.WIDTH - (Chooser.WIDTH))/2);
    public static int y = ((Consts.HEIGHT)/2 + 3*OCo.THICKNESS/2);

    public static int count_x = 0;
    public static int count_y = 0;

    public static boolean Choosen = false;
}
