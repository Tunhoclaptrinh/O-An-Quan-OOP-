package OCo;
import GameGUI.Consts;

public abstract class OCo {
    protected int index;
    public static int THICKNESS = 6;
    public static final int x = (Consts.WIDTH - (5*ODan.WIDTH + OQuan.WIDTH))/2;
    public static final int y = (Consts.HEIGHT - (2*ODan.HEIGHT))/2;

    //Constructor
    public OCo(int index) {
        this.index = index;
    }

    //Getter
    public int getIndex() {
        return index;
    }

    //Setter
    public void setIndex(int index) {
        this.index = index;
    }

}
