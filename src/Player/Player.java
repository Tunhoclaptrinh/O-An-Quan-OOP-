package Player;

import Da.Dan;
import Da.Quan;

import java.util.ArrayList;

public class Player {
    private String name;
    private /*static*/ int index = 0;
//    private /*static*/ int score = 0;
    private /*static*/ int diemCong = 0;

    // Các thuộc tính dùng để lưu điểm của người chơi.
    // => Còn có mục đích phục vụ cho hàm rải thêm Dân vào ô khi rơi vào trường hợp còn game, đến lượt nhưng không có Dân trên bàn cờ phía mình để rải.
    private ArrayList<Quan> quans = new ArrayList<>();
    private ArrayList<Dan> dans = new ArrayList<>();


    //Constructor
    public Player(String name) {
        this.name = name;
        this.index += 1;
    }

    //Getter
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
//    public int getScore() {
//        return score;
//    }
    public int getDiemCong() {
        return diemCong;
    }


    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setIndex(int index) {
        this.index = index;
    }
//    public void setScore(int score) {
//        this.score = score;
//    }
    public void setDiemCong(int diemCong) {
        this.diemCong = diemCong;
    }




    public void setDans(ArrayList<Dan> dans) {
         this.dans.addAll(dans);
         System.out.println(dans);
    }

    public void setQuans(ArrayList<Quan> quans){
        this.quans.addAll ((ArrayList<Quan>) quans.clone());
        System.out.println(quans);
    }

    //Method

    public int sumQuanAndDans(){
        if (this.quans.isEmpty()){
            return dans.size()*Dan.getScore();
        }
        else {
            return this.quans.size()*Quan.getScore() + this.dans.size()*Dan.getScore();
        }
    }
}
