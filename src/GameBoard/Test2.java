package GameBoard;


import Da.Dan;
import Da.Quan;
import KhoiTao.KhoiTao;
import OCo.ODan;
import OCo.OQuan;
import Player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Test2 {

    public static Player player1 = new Player("Tuấn");
    public static Player player2 = new Player("Tún");

    private static int currentPlayer = 0; //đếm người chơi => Xoay vòng chơi
//    private static int scored01 = player1.getScore();
//    private static int scored02 = player2.getScore();
    private static int count = 0;
    private static Scanner scanner = new Scanner(System.in);

    // Initialize the board
    public static ArrayList gameBoard = new ArrayList<>();
    public static ArrayList<ODan> oDans = KhoiTao.KhoiTaoODan();
    public static ArrayList<Dan> dans = KhoiTao.KhoiTaoDan();
    public static ArrayList<OQuan> oQuans = KhoiTao.KhoiTaoOQuan();
    public static ArrayList<Quan> quans = KhoiTao.KhoiTaoQuan();

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
        count = 0;
        while (true) {
            count++;
            if (currentPlayer == 2) {
                currentPlayer = 0;
            }
            printBoard();

            if (oQuans.get(0).sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) == 0 && oQuans.get(1).sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) == 0) {
//                scored01 += sumRange(0, 5);
//                player1.setScore(scored01);
//                scored02 += sumRange(6, 11);
//                player2.setScore(scored02);

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

    private static void phanphoi(int hole, String chieu, int i) {
        if (chieu.equals("t")) {
            // Distribute stones to the left
            ArrayList stones = oDans.get(hole).getDans() ;
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
                        stones = oQuans.get(1).getDans();
                    }
                    else if (i - 1 == 5) {
                        stones = oQuans.get(0).getDans();
                    }
                    else {stones = oDans.get(i-1).getDans();}
                }
                i--;
                printBoard();
            }

            // Capture stones
            ArrayList diemCong = new ArrayList();
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

            while (stones.isEmpty()) {
                if (i < 0) i = 11;

                //Điều kiện dừng khi dân trong Ô Quan trống nhưng Ô Quan vẫn chứa Quan
                if (i+1==oQuans.get(0).getIndex() && !oQuans.get(0).getQuan().isEmpty()) {
                    break;
                }
                else if (i+1==oQuans.get(1).getIndex() && !oQuans.get(1).getQuan().isEmpty()) {
                    break;
                }
                //Ăn quân. Thêm các Dân thuộc Ô Quan vào điểm của người chơi
                if (i==oQuans.get(0).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) >= 15) {
                        if (currentPlayer == 0) {
                            player1.setQuans(oQuans.get(0).getQuan());
                        } else {
                            player2.setQuans(oQuans.get(0).getQuan());
                        }
                        oQuans.get(0).getQuan().clear();
                    }
                    stones = oQuans.get(0).getDans();
                }
                else if (i==oQuans.get(1).getIndex()) {
                    if (OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) >= 15) {
                        if (currentPlayer == 0) {
                            player1.setQuans(oQuans.get(1).getQuan());
                        } else {
                            player2.setQuans(oQuans.get(1).getQuan());
                        }
                        oQuans.get(1).getQuan().clear();
                    }
                    stones = oQuans.get(1).getDans();
                }
                else {
                    if (i==5) i=4;
                    stones = oDans.get(i).getDans() ;
                }

                if (!stones.isEmpty()) {

                    diemCong =(ArrayList) stones.clone();
                    if (currentPlayer == 0) {
                        player1.setDans(diemCong);
                    }
                    else if (currentPlayer == 1) {
                        player2.setDans(diemCong);
                    }

                    stones.clear();

                    i--;
                    if (i < 0) i = 11;
                    if (i==oQuans.get(0).getIndex()) {
//                        if (OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) >= 15) {
//                            if (currentPlayer == 0) {
//                                player1.setQuans(oQuans.get(0).getQuan());
//                            } else {
//                                player2.setQuans(oQuans.get(0).getQuan());
//                            }
//                            oQuans.get(0).getQuan().clear();
//                        }
                        stones = oQuans.get(0).getDans();
                    }
                    else if (i==oQuans.get(1).getIndex()) {
//                        if (OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) >= 15) {
//                            if (currentPlayer == 0) {
//                                player1.setQuans(oQuans.get(1).getQuan());
//                            } else {
//                                player2.setQuans(oQuans.get(1).getQuan());
//                            }
//                            oQuans.get(1).getQuan().clear();
//                        }
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

            printScore(diemCong.size());

        } else if (chieu.equals("p")) {
            // Distribute stones to the right
            ArrayList stones = oDans.get(hole).getDans() ;
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
                        stones = oDans.get(0).getDans();
                    }
                    else if (i + 1 == 5) {
                        stones = oQuans.get(0).getDans();
                    }
                    else if (i + 1 == 11) {
                        stones = oQuans.get(1).getDans();
                    }
                    else {
                        stones = oDans.get(i+1).getDans();
                    }
                }
                i++;
                printBoard();
            }

            // Capture stones
            ArrayList diemCong = new ArrayList<>();
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


//  2t 3t 1t 6p
//  2t/p 6p/10t 8t/p 0p/4t 1t/3p 9t/7p 3p/1t
//  8t/p 0p/4t 2t/p 6p/10t 7t/9p 3t/1p 9p/7t
//  6t
// 2t 6p 8t 0p 1t 3p 6t 10t 4p 2p



            while (stones.isEmpty()) {
                if (i > 11) i = 0;

                //Điều kiện dừng khi dân trong Ô Quan trống nhưng Ô Quan vẫn chứa Quan
                if (i-1==oQuans.get(0).getIndex() && !oQuans.get(0).getQuan().isEmpty()) {
                    break;
                }
                else if (i-1==oQuans.get(1).getIndex() && !oQuans.get(1).getQuan().isEmpty()) {
                    break;
                }

                //Ăn quân. Thêm các Dân thuộc Ô Quan vào điểm của người chơi
                if (i==oQuans.get(0).getIndex()) {
//                    if (OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()) >= 15) {
//                        if (currentPlayer == 0) {
//                            player1.setQuans(oQuans.get(0).getQuan());
//                        } else {
//                            player2.setQuans(oQuans.get(0).getQuan());
//                        }
//                        oQuans.get(0).getQuan().clear();
//                    }
                    stones = oQuans.get(0).getDans();
                }
                else if (i==oQuans.get(1).getIndex()) {
//                    if (OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) >= 15) {
//                        if (currentPlayer == 0) {
//                            player1.setQuans(oQuans.get(1).getQuan());
//                        } else {
//                            player2.setQuans(oQuans.get(1).getQuan());
//                        }
//                        oQuans.get(1).getQuan().clear();
//                    }
                    stones = oQuans.get(1).getDans();
                }
                else {
                    if (i==5) i=4;
                    stones = oDans.get(i).getDans() ;
                }

                if (!stones.isEmpty()) {
                    diemCong =(ArrayList) stones.clone();
                    if (currentPlayer == 0) {
                        player1.setDans(diemCong);
                    }
                    else if (currentPlayer == 1) {
                        player2.setDans(diemCong);
                    }

                    stones.clear();

                    i++;
                    if (i > 11) i = 0;
                    if (i==oQuans.get(0).getIndex()) {
                        if (currentPlayer == 0) {
                            player1.setQuans(oQuans.get(0).getQuan());
                        } else {
                            player2.setQuans(oQuans.get(0).getQuan());
                        }
                        stones = oQuans.get(0).getDans();
                    }
                    else if (i==oQuans.get(1).getIndex()) {
                        if (currentPlayer == 0) {
                            player1.setQuans(oQuans.get(1).getQuan());
                        } else {
                            player2.setQuans(oQuans.get(1).getQuan());
                        }
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
                if (i==oQuans.get(0).getIndex()) {
                    if (!oQuans.get(0).getQuan().isEmpty()) {
                        break;
                    }
                }
                if (i==oQuans.get(1).getIndex()) {
                    if (!oQuans.get(1).getQuan().isEmpty()) {
                        break;
                    }
                }

                printBoard();
            }

            printScore(diemCong.size());
        }

        currentPlayer++;
//        raithhem();
    }

    //Rải thêm Dân vào ô khi rơi vào trường hợp còn game, đến lượt nhưng không có Dân trên bàn cờ phía mình để rải.
//    private static void raithhem() {
//        if (currentPlayer == 0 || currentPlayer == 2) {
//            if (sumRange(0, 5) == 0) {
//                if (scored01 == 0) {
//                    printFinalScore();
//                }
//                if (scored01 > 0 && scored01 <= 5) {
//                    int newStones = scored01;
//                    scored01 = 0;
//                    for (int z = 0; z < 5; z++) {
//                        while (newStones > 0) {
//                            board[z]++;
//                            newStones--;
//                        }
//                    }
//                }
//                if (scored01 > 5) {
//                    scored01 -= 5;
//                    Arrays.fill(board, 0, 5, 1);
//                }
//            }
//        }
//
//        if (currentPlayer == 1) {
//            if (sumRange(6, 11) == 0) {
//                if (scored02 == 0) {
//                    printFinalScore();
//                }
//                if (scored02 > 0 && scored02 <= 5) {
//                    int newStones = scored02;
//                    scored02 = 0;
//                    for (int z = 6; z < 11; z++) {
//                        while (newStones > 0) {
//                            board[z]++;
//                            newStones--;
//                        }
//                    }
//                }
//                if (scored02 > 5) {
//                    scored02 -= 5;
//                    Arrays.fill(board, 6, 11, 1);
//                }
//            }
//        }
//    }


    //Hàm tính toán điểm (số dân, thêm số dân vào các thuộc tính lưu điểm của người chơi) còn lại trên bàn cờ ngay khi hai Ô Quan không còn điểm nữa
    private static int sumRange(int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += oDans.get(i).sumDans(oDans.get(i).getDans());
        }
        return sum;
    }

    // Hiển thị số điểm được cộng của người chơi sau mỗi lượt rải Dân
    private static void printScore(int diemCong) {
        if (currentPlayer == 0) {
            System.out.println("Người chơi " + player1.getName() + " nhận được: " + diemCong);
            System.out.println("Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans());
        }
        if (currentPlayer == 1) {
            System.out.println("Người chơi " + player2.getName() + " nhận được: " + diemCong);
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