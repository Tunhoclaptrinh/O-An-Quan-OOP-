package OCo;

import Da.Quan;
import Da.Dan;

import java.awt.*;
import java.util.ArrayList;

public class OQuan extends OCo {

    private static int OQuanCount = 0;
    public static Color oQuanColor = Color.WHITE;
    public static final int WIDTH = 2*ODan.WIDTH;
    public static final int HEIGHT = 2*ODan.HEIGHT;
    private ArrayList<Quan> quans = new ArrayList<>();
    private ArrayList<Dan> dans = new ArrayList<>();



    //Constructor
    public OQuan(int index){
        super(index);
        OQuanCount++;
    }

    //Getter
    public ArrayList<Quan> getQuan(){return quans;}
    public ArrayList<Dan> getDans(){return dans;}



    //Setter
    public void setDans(Dan dan) {
        if (dan == null) {
            this.dans.clear();
        }
        else {
            this.dans.add(dan);
            System.out.println("Added Dan: " + dan + " | Current size: " + this.dans.size());
        }

    }

    public void setQuan(Quan quan){
        if (this.quans == null) {
            this.quans= new ArrayList<>();
        }
        this.quans.add(quan);
        System.out.println("Added Quan: " + quan + " | Current size: " + this.quans.size());
    }

    //Method
    public static int sumQuanAndDans(ArrayList<Quan> quan, ArrayList<Dan> dans){
        if (quan.size() != 1){
            return dans.size()*Dan.getScore();
        }
        else {
            return quan.size()*Quan.getScore() + dans.size()*Dan.getScore();
        }
    }
    public void clearDans() {
        if (this.dans != null) {
            this.dans.clear(); // Xóa danh sách nhưng vẫn giữ danh sách tồn tại
        }
    }


    @Override
    public String toString() {
        return "OQuan{" +
                "index=" + index +
                '}';
    }
}
