package OCo;

import Da.Dan;

import java.awt.*;
import java.util.ArrayList;

public class ODan extends OCo {

    public static Color oDanColor = Color.WHITE ;
    public static final int DEFAULT_STONES = 5;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;
    private ArrayList<Dan> dans = new ArrayList<>();


    //Constructor
    public ODan(int index) {
        super(index);
    }

    //Getter
    public ArrayList<Dan> getDans() {return this.dans;}



    //Setter
    public void setDans(Dan dan) {
        if (this.dans == null) {
            this.dans = new ArrayList<>();
        }
        this.dans.add(dan);
        System.out.println("Added Dan: " + dan + " | Current size: " + this.dans.size());
    }



    public void clearDans() {
        if (this.dans != null) {
            this.dans.clear(); // Xóa danh sách nhưng vẫn giữ danh sách tồn tại
        }
    }
    public void addDan(Dan dan) {
        this.dans.add(dan);
    }



    //Method
    public static int sumDans (ArrayList<Dan> dans) {
        int sumDans = dans.size();
        return sumDans;
    }


    @Override
    public String toString() {
        return "ODan{" +
                "index=" + index +
                '}';
    }
}
