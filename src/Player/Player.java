package Player;

import Da.Dan;
import Da.Quan;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Scanner;

public class Player {
    private String name;
    private int player_id = -1;
    private char direction;
=======

public class Player {
    private String name;
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd


    // Các thuộc tính dùng để lưu điểm của người chơi.
    // => Còn có mục đích phục vụ cho hàm rải thêm Dân vào ô khi rơi vào trường hợp còn game, đến lượt nhưng không có Dân trên bàn cờ phía mình để rải.
    private ArrayList<Quan> quans = new ArrayList<>();
    private ArrayList<Dan> dans = new ArrayList<>();


    //Constructor
    public Player(String name) {
        this.name = name;
    }

    //Getter
    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public int getPlayer_id() {
        return player_id;
    }

=======
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd
    public ArrayList<Dan> getDans() {
        return dans;
    }

    public ArrayList<Quan> getQuans() {
        return quans;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public void setPlayer_id() {
        this.player_id += 1;
    }

=======
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd
    public void setDans(ArrayList<Dan> dans) {
         this.dans.addAll(dans);
         System.out.println(dans);
    }

    public void setQuans(ArrayList<Quan> quans){
        this.quans.addAll ((ArrayList<Quan>) quans.clone());
        System.out.println(quans);
    }
<<<<<<< HEAD
    public void setDirection(char direction){
        Scanner sc = new Scanner(System.in);
        direction = sc.next().charAt(0);
        this.direction = direction;
        sc.close();
    }

    //Method



=======

    //Method
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd
//    public int sumDans() {
//        return this.dans.size()*Dan.getScore();
//    }

<<<<<<< HEAD

=======
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd
    public int sumQuanAndDans(){
        if (this.quans.isEmpty()){
            return dans.size()*Dan.getScore();
        }
        else {
            return this.quans.size()*Quan.getScore() + this.dans.size()*Dan.getScore();
        }
    }
<<<<<<< HEAD


=======
>>>>>>> 5150858ca480a931e5748ac80edb055edd08a9fd
}
