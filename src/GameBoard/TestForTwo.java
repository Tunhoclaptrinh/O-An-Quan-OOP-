package GameBoard;


import Da.Dan;
import Da.Quan;
import Initialization.InitializationForTwo;
import OCo.ODan;
import OCo.OQuan;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class TestForTwo {

    public static Player player1 = new Player("Tún");
    public static Player player2 = new Player("Tuấn");


    private static int currentPlayer = player2.getPlayer_id(); //đếm người chơi => Xoay vòng chơi
//    private static int scored01 = player1.getScore();
//    private static int scored02 = player2.getScore();
    private static int count = 0; //Điều kiện ăn Quan ở vòng chơi thú 3
    private static Scanner scanner = new Scanner(System.in);

    // Initialize the board
    public static ArrayList gameBoard = new ArrayList<>();

    public static InitializationForTwo init = new InitializationForTwo();
    public static ArrayList<ODan> oDans = init.InitODan();
    public static ArrayList<Dan> dans = init.InitDan();
    public static ArrayList<OQuan> oQuans = init.InitOQuan();
    public static ArrayList<Quan> quans = init.InitQuan();

//    public static ArrayList<ODan> oDans = (ArrayList<ODan>) KhoiTao.KhoiTaoODan().clone();
//    public static ArrayList<Dan> dans = (ArrayList<Dan>) KhoiTao.KhoiTaoDan().clone();
//    public static ArrayList<OQuan> oQuans = (ArrayList<OQuan>) KhoiTao.KhoiTaoOQuan().clone();
//    public static ArrayList<Quan> quans = (ArrayList<Quan>) KhoiTao.KhoiTaoQuan().clone();

    public static void main(String[] args) {

        //Thêm Dân vào Ô Dân
        int danIndex = 0;
        for (int i = 0; i < oDans.size()  ; i++) {
            for (int j = 0; j < ODan.DEFAULT_STONES ; j++) {
                oDans.get(i).setDans(dans.get(danIndex));
                danIndex++;
            }
        }

        oDans.add(5,null);

//        cái này là tạo 12 ô quan tránh lỗi logic khi i chạy
//        for (int i = 0; i < 12  ; i++) {
//            if (i != 0 && i != 11){
//                oQuans.add(i,null);
//            }
//        }
//        System.out.println(oQuans);

        //Thêm Quan và 0 Dân vào Ô Quan
        oQuans.get(0).setQuan(quans.get(0));
        oQuans.get(1).setQuan(quans.get(1));
        oQuans.get(0).setDans(null);
        oQuans.get(1).setDans(null);

        //Thêm các Ô Cờ vào Bàn Cờ
        for (int i = 0; i <12; i++){
            if (i == oQuans.get(0).getIndex()){
                gameBoard.add(oQuans.get(0));
            }
            else if (i == oQuans.get(1).getIndex()){
                gameBoard.add(oQuans.get(1));
            }
            else {
                if (i == 6) i--;
                gameBoard.add(oDans.get(i));
                i++;
            }
        }

        if (oDans == null || oQuans == null || oDans.size() < 11 || oQuans.size() < 2) {
            throw new IllegalStateException("Bàn cờ không hợp lệ. Kiểm tra lại việc khởi tạo!");
        }

        playGame();
        scanner.close();
    }

    // Hàm in bàn cờ
    private static void printBoard() {
        System.out.print("     | ");
        for (int k = 10; k > 5; k--) {
            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " | ");
        }
        System.out.println();
        System.out.println("["+ OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) + "," + oQuans.get(1).getDans().size() + "]                   [" + oQuans.get(0).getDans().size() + "," + OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans())+ "]");
        System.out.print("     | ");
        for (int k = 0; k < 5; k++) {
            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " | ");
        }
        System.out.println("\n");
    }

    private static void playGame() {
        while (true) {
            if (currentPlayer == 2) {
                currentPlayer = 0;
            }
            printBoard();

            if (oQuans.get(0).sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) == 0 && oQuans.get(1).sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) == 0) {
                player1.setDans(sumRange(0, 5));
                player2.setDans(sumRange(6, 11));
                System.out.println("Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans());
                System.out.println("Điểm của " + player2.getName() + ": " + player2.sumQuanAndDans());

                if (player1.sumQuanAndDans() < player2.sumQuanAndDans()) {
                    System.out.println("Player:" + player2.getName()  + ": Win!");
                } else if (player2.sumQuanAndDans() < player1.sumQuanAndDans()) {
                    System.out.println("Player:" + player1.getName()  + ": Win!");
                } else {
                    System.out.println("Hòa");
                }
                break;
            }

            int hole;
            if (currentPlayer == 0) {
                System.out.println("Player1: "+ player1.getName());
                System.out.print("Chọn lỗ (0-4) <=> (1-5): ");
                hole = scanner.nextInt();
                if (hole == 5 || hole == 11 || oDans.get(hole).sumDans(oDans.get(hole).getDans()) == 0) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    continue;
                }
            } else {
                System.out.println("Player2: " + player2.getName());
                System.out.print("Chọn lỗ (6-10) <=> (1-5): ");
                hole = scanner.nextInt();
                if (hole == 5 || hole == 11 || oDans.get(hole).sumDans(oDans.get(hole).getDans()) == 0) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    continue;
                }
            }

            System.out.print("Chọn chiều Phải - Trái (p/t): ");
            String chieu = scanner.next();

            int i = chieu.equals("t") ? hole - 1 : hole + 1;
            phanphoi(hole, chieu, i);
            printBoard();
        }
    }
    //  Kiểm tra ăn liên tục với Quan 2t 7t 1t 8t 1p 10p 2t 8t 4p 10p 4p 7t
    private static void phanphoi(int hole, String chieu, int i) {
        if (chieu.equals("t")) {

            // Distribute stones to the left
            ArrayList stones = (ArrayList) (oDans.get(hole).getDans()).clone();

            oDans.get(hole).getDans().clear();
            while (!stones.isEmpty()) {
                Dan dan_temp = (Dan) stones.get(stones.size()-1);

                if (i < 0) i = 11;
                if (i==oQuans.get(0).getIndex()) {
                    oQuans.getFirst().setDans(dan_temp);
                }
                else if (i==oQuans.get(1).getIndex()) {
                    oQuans.get(1).setDans(dan_temp);
                }
                else {
                    oDans.get(i).setDans(dan_temp);
                }
                stones.remove(stones.size() - 1);

                if (stones.isEmpty()) {
                    if (i - 1 == -1){
                        stones = (ArrayList) oQuans.get(1).getDans().clone();
                        oQuans.get(1).getDans().clear();
                        i--;
                    }
                    else if (i - 1 == 5) {
                        stones = (ArrayList) oQuans.get(0).getDans().clone();
                        oQuans.get(0).getDans().clear();
                        i--;
                    }
                    else {
                        stones = (ArrayList) oDans.get(i-1).getDans().clone();
                        oDans.get(i-1).getDans().clear();
                        i--;
                    }
                    if (i < 0) i = 11;
                }
                i--;

                printBoard();
            }

            // Capture stones
            ArrayList diemCong = new ArrayList();
            int luu_diemCong = 0;
            ArrayList quanCong = new ArrayList();
            int luu_quanCong = 0;

            if (i < 0) i = 11;

            if (i+1==oQuans.get(0).getIndex()) {
                stones = (ArrayList) oQuans.get(0).getDans().clone();
                oQuans.get(0).getDans().clear();
            }
            else if (i+1==oQuans.get(1).getIndex()) {
                stones = (ArrayList) oQuans.get(1).getDans().clone();
                oQuans.get(1).getDans().clear();
            }

            else if (i+1 == 12){
                stones = (ArrayList) oDans.get(0).getDans().clone();
                oDans.get(0).getDans().clear();
            }

            else {
                stones = (ArrayList) oDans.get(i+1).getDans().clone();
                oDans.get(i+1).getDans().clear();
            }
//            i--;


            //Vòng while để ăn liên tục
            while (stones.isEmpty()) {
                if (i < 0) i = 11;

                //Điều kiện dừng khi dân trong Ô Quan trống nhưng Ô Quan vẫn chứa Quan
                if (i+1==oQuans.get(0).getIndex() && !oQuans.get(0).getQuan().isEmpty()) {
                    break;
                }
                else if (i+1==oQuans.get(1).getIndex()) {
                    if (!oQuans.get(1).getQuan().isEmpty()){
                        break;
                    }
                }


                //Ăn quân. Thêm các Dân thuộc Ô Quan vào điểm của người chơi
                if (i==oQuans.get(0).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) >= 15 || count > 2) {
                        quanCong = (ArrayList) oQuans.get(0).getQuan().clone();
                        luu_quanCong += quanCong.size();
                        oQuans.get(0).getQuan().clear();
                    }
                    stones = oQuans.get(0).getDans();
                }
                else if (i==oQuans.get(1).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) >= 15 || count > 2) {
                        quanCong = (ArrayList) oQuans.get(1).getQuan().clone();
                        luu_quanCong += quanCong.size();
                        oQuans.get(1).getQuan().clear();
                    }
                    stones = oQuans.get(1).getDans();
                }
                else {
                    if (i==5) i=4;
                    stones = oDans.get(i).getDans() ;
                }

                if (!stones.isEmpty() || !quanCong.isEmpty()) {

                    diemCong =(ArrayList) stones.clone();
                    luu_diemCong += diemCong.size();

                    if (currentPlayer == 0) {
                        player1.setQuans(quanCong);
                        player1.setDans(diemCong);
                    }
                    else if (currentPlayer == 1) {
                        player2.setQuans(quanCong);
                        player2.setDans(diemCong);
                    }

                    stones.clear();
                    quanCong.clear();

                    i--;
                    if (i < 0) i = 11;
                    if (i==oQuans.get(0).getIndex()) {
                        stones = oQuans.get(0).getDans();
                    }
                    else if (i==oQuans.get(1).getIndex()) {
                        stones = oQuans.get(1).getDans();
                    }
                    else {
                        stones = oDans.get(i).getDans() ;
                    }
                    i--;
                } else if (stones.size() == 0) {
                    break;
                }

                if (i < 0) i = 11;

                printBoard();
            }

            printScore(luu_diemCong, luu_quanCong );

        } else if (chieu.equals("p")) {
            // Distribute stones to the right
            ArrayList stones = (ArrayList) (oDans.get(hole).getDans()).clone();
            oDans.get(hole).getDans().clear();
            while (!stones.isEmpty()) {
                Dan dan_temp = (Dan) stones.get(stones.size()-1);

                if (i > 11) i = 0;
                if (i==oQuans.get(0).getIndex()) {
                    oQuans.getFirst().setDans(dan_temp);
                }
                else if (i==oQuans.get(1).getIndex()) {
                    oQuans.get(1).setDans(dan_temp);
                }
                else {
                    oDans.get(i).setDans(dan_temp);
                }
                stones.remove(stones.size() - 1);


                if (stones.isEmpty()) {
                    if (i + 1 == 12){
                        stones = (ArrayList) oDans.get(0).getDans().clone();
                        oDans.get(0).getDans().clear();
                        i++;
                    }
                    else if (i + 1 == 5) {
                        stones = (ArrayList) oQuans.get(0).getDans().clone();
                        oQuans.get(0).getDans().clear();
                        i++;
                    }
                    else if (i + 1 == 11) {
                        stones = (ArrayList) oQuans.get(1).getDans().clone();
                        oQuans.get(1).getDans().clear();
                        i++;
                    }
                    else {
                        stones = (ArrayList) oDans.get(i+1).getDans().clone();
                        oDans.get(i+1).getDans().clear();
                        i++;
                    }
                    if (i>11) i = 0;
                }
                i++;
                printBoard();
            }

            // Capture stones
            ArrayList diemCong = new ArrayList<>();
            int luu_diemCong = 0;
            ArrayList quanCong = new ArrayList();
            int luu_quanCong = 0;
            if (i > 11) i = 0;

            if (i-1==oQuans.get(0).getIndex()) {
                stones = (ArrayList) oQuans.get(0).getDans().clone();
                oQuans.get(0).getDans().clear();
            }
            else if (i-1==oQuans.get(1).getIndex()) {
                stones = (ArrayList) oQuans.get(1).getDans().clone();
                oQuans.get(1).getDans().clear();
            }
            else if (i-1==-1) {
                stones = (ArrayList) oQuans.get(1).getDans().clone();
                oQuans.get(1).getDans().clear();

            }
            else {
                stones = (ArrayList) oDans.get(i-1).getDans().clone();
                oDans.get(i-1).getDans().clear();
            }
//            i++;

            //Vòng while để ăn liên tục
            while (stones.isEmpty()) {
                if (i > 11) i = 0;

                //Điều kiện dừng khi dân trong Ô Quan trống nhưng Ô Quan vẫn chứa Quan
                if (i-1==oQuans.get(0).getIndex() && !oQuans.get(0).getQuan().isEmpty()) {
                    break;
                }
                else if (i-1==oQuans.get(1).getIndex() || i - 1 == -1) {
                    if (!oQuans.get(1).getQuan().isEmpty()){
                        break;
                    }
                }

                //Ăn quân. Thêm các Dân thuộc Ô Quan vào điểm của người chơi
                if (i==oQuans.get(0).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) >= 15 || count > 2) {
                        quanCong = (ArrayList) oQuans.get(0).getQuan().clone();
                        luu_quanCong += quanCong.size();
                        oQuans.get(0).getQuan().clear();
                    }
                    stones = oQuans.get(0).getDans();
                }
                else if (i==oQuans.get(1).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) >= 15 || count > 2) {
                        quanCong = (ArrayList) oQuans.get(1).getQuan().clone();
                        luu_quanCong += quanCong.size();
                        oQuans.get(1).getQuan().clear();
                    }
                    stones = oQuans.get(1).getDans();
                }
                else {
                    if (i==5) i=4;
                    stones = oDans.get(i).getDans() ;
                }

                if (!stones.isEmpty() || !quanCong.isEmpty()) {
                    diemCong =(ArrayList) stones.clone();
                    luu_diemCong += diemCong.size();

                    if (currentPlayer == 0) {
                        player1.setQuans(quanCong);
                        player1.setDans(diemCong);
                    }
                    else if (currentPlayer == 1) {
                        player2.setQuans(quanCong);
                        player2.setDans(diemCong);
                    }

                    stones.clear();
                    quanCong.clear();


                    i++;
                    if (i > 11) i = 0;
                    if (i==oQuans.get(0).getIndex()) {
                        stones = oQuans.get(0).getDans();
                    }
                    else if (i==oQuans.get(1).getIndex()) {
                        stones = oQuans.get(1).getDans();
                    }
                    else {
                        stones = oDans.get(i).getDans() ;
                    }
                    i++;

                } else if (stones.size() == 0) {
                    break;
                }


                if (i > 11) i = 0;

//                if (i==oQuans.get(0).getIndex() && !stones.isEmpty()) {
//                    if (!oQuans.get(0).getQuan().isEmpty()) {
//                        break;
//                    }
//                }
//                if (i==oQuans.get(1).getIndex() && !stones.isEmpty()) {
//                    if (!oQuans.get(1).getQuan().isEmpty() ) {
//                        break;
//                    }
//                }

                printBoard();
            }

            printScore(luu_diemCong, luu_quanCong);
        }

        currentPlayer++;
        count ++;
        raithhem();
    }

    //Rải thêm Dân vào ô khi rơi vào trường hợp còn game, đến lượt nhưng không có Dân trên bàn cờ phía mình để rải.
    private static void raithhem() {
        if (currentPlayer == player1.getPlayer_id() || currentPlayer == player1.getPlayer_id() + 2) {
            if (sumRange(0, 5).isEmpty()) {
                if (player1.getDans().size() == 0) {
                    printFinalScore();
                }
                if (player1.getDans().size() > 0 && player1.getDans().size() <= 5) {
                    ArrayList newStones = (ArrayList) player1.getDans().clone();
                    player1.getDans().clear();
                    for (int z = oDans.getFirst().getIndex(); z < oDans.get(5).getIndex(); z++) {
                        Dan newDan_temp = (Dan) newStones.getLast();

                        oDans.get(z).setDans(newDan_temp);
                        newStones.removeLast();
                    }
                }
                if (player1.getDans().size() > 5) {
                    ArrayList newStones = new ArrayList();
                    for (int i = 0; i < 5 ; i++){
                        newStones.add(player1.getDans().get(i));
                        player1.getDans().remove(i);
                    }
                    for (int z = oDans.getFirst().getIndex(); z <= oDans.get(4).getIndex(); z++) {
                        Dan newDan_temp = (Dan) newStones.getLast();

                        oDans.get(z).setDans(newDan_temp);
                        newStones.removeLast();
                    }
                }
            }
        }

        if (currentPlayer == player2.getPlayer_id()) {
            if (sumRange(6, 11).isEmpty()) {
                if (player2.getDans().size() == 0) {
                    printFinalScore();
                }
                if (player2.getDans().size() > 0 && player2.getDans().size() <= 5) {
                    ArrayList newStones = (ArrayList) player2.getDans().clone();
                    player2.getDans().clear();
                    for (int z = oDans.get(6).getIndex(); z <= oDans.getLast().getIndex(); z++) {
                        Dan newDan_temp = (Dan) newStones.getLast();

                        oDans.get(z).setDans(newDan_temp);
                        newStones.removeLast();
                    }
                }
                if (player2.getDans().size() > 5) {
                    ArrayList newStones = new ArrayList();
                    for (int i = 0; i < 5 ; i++){
                        newStones.add(player2.getDans().get(i));
                        player2.getDans().remove(i);
                    }
                    for (int z = oDans.get(6).getIndex(); z <= oDans.getLast().getIndex(); z++) {
                        Dan newDan_temp = (Dan) newStones.getLast();

                        oDans.get(z).setDans(newDan_temp);
                        newStones.removeLast();
                    }
                }
            }
        }
    }


    //Hàm tính toán điểm (số dân, thêm số dân vào các thuộc tính lưu điểm của người chơi) còn lại trên bàn cờ ngay khi hai Ô Quan không còn điểm nữa
    private static ArrayList<Dan> sumRange(int start, int end) {
        ArrayList<Dan> sumRange = new ArrayList<>();
        for (int i = start; i < end; i++) {
            ArrayList<Dan> dans = (ArrayList<Dan>) oDans.get(i).getDans().clone();

            sumRange.addAll(dans);
            dans.clear();
            printBoard();
        }
        return sumRange;
    }

    // Hiển thị số điểm được cộng của người chơi sau mỗi lượt rải Dân
    private static void printScore(int diemCong, int quanCong) {
        if (currentPlayer == 0) {
        System.out.println("Người chơi " + player1.getName() + " nhận được: " + diemCong + " Dân, " + quanCong + " Quan");
            System.out.println("Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans());
        }
        if (currentPlayer == 1) {
            System.out.println("Người chơi " + player2.getName() + " nhận được: " +  diemCong + " Dân, " + quanCong + " Quan");
            System.out.println("Điểm của " + player2.getName() + ": " + player2.sumQuanAndDans());
        }
    }

    // In ra điểm cuối cùng và tuyên bố kết quả kết thúc màn chơi.
    private static void printFinalScore() {
        System.out.println("Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans());
        System.out.println("Điểm của " + player2.getName() + ": " + player2.sumQuanAndDans());
        if (player1.sumQuanAndDans() < player2.sumQuanAndDans()) {
            System.out.println(player1.getName() +": Win!");
        } else if (player2.sumQuanAndDans() < player1.sumQuanAndDans()) {
            System.out.println(player2.getName() +": Win!");
        } else {
            System.out.println("Hòa");
        }
    }
}