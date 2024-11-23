package Player;

import Da.Dan;
import Da.Quan;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private int player_id = -1;
    private int hole;
    private char direction;
    private static Scanner scanner = new Scanner(System.in); // Sử dụng `scanner` dùng chung


    // Các thuộc tính dùng để lưu điểm của người chơi.
    // => Còn có mục đích phục vụ cho hàm rải thêm Dân vào ô khi rơi vào trường hợp còn game, đến lượt nhưng không có Dân trên bàn cờ phía mình để rải.
    private ArrayList<Quan> quans = new ArrayList<>();
    private ArrayList<Dan> dans = new ArrayList<>();


    //Constructor
    public Player(String name) {
        this.name = name;
        this.player_id += 1; //id người chơi tăng tự đông => xoay vòng người chơi
    }

    //Getter
    public String getName() {
        return name;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public int getHole() {
        return hole;
    }

    public char getDirection() {
        return direction;
    }

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

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public void setDans(ArrayList<Dan> dans) {
         this.dans.addAll(dans);
         System.out.println(dans);
    }

    public void setQuans(ArrayList<Quan> quans){
        this.quans.addAll ((ArrayList<Quan>) quans.clone());
        System.out.println(quans);
    }

    //Chọn lỗ bắt đá
    public void setHole () {
        // Chơi với máy
        if (this.name.equals("Máy") ) {
            this.hole = new Random().nextInt(6,11);
            System.out.println(this.hole);
        }

        // Chơi hai người bình thường
        else {
            this.hole = scanner.nextInt();
        }

    }

    //chọn chiều phân phối
    public void setDirection(){

        // Chơi với máy
        if (this.name.equals("Máy")) {
            boolean rd = new Random().nextBoolean();

            if (rd == true) {
                this.direction = 't';
            }
            else {
                this.direction = 'p';
            }
            System.out.println(this.direction);
        }

        // Chơi hai người bình thường
        else {
            this.direction = scanner.next().charAt(0);
        }

    }

    //Method


//    public int sumDans() {
//        return this.dans.size()*Dan.getScore();
//    }


    // Hàm tính điểm
    public int sumQuanAndDans(){
        if (this.quans.isEmpty()){
            return dans.size()*Dan.getScore();
        }
        else {
            return this.quans.size()*Quan.getScore() + this.dans.size()*Dan.getScore();
        }
    }


}
