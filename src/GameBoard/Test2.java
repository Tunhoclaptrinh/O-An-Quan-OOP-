package GameBoard;


import Da.Dan;
import Da.Quan;
import KhoiTao.KhoiTao;
import OCo.ODan;
import OCo.OQuan;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Test2 {

    public static Player player1 = new Player("Tuấn");
    public static Player player2 = new Player("Tún");

    private static int currentPlayer = 0;
    private static int scored01 = player1.getScore();
    private static int scored02 = player2.getScore();
    private static int count = 0; //đếm người chơi => Xoay vòng chơi
    private static Scanner scanner = new Scanner(System.in);

    // Initialize the board
    public static ArrayList gameBoard = new ArrayList<>();
    public static ArrayList<ODan> oDans = KhoiTao.KhoiTaoODan();
    public static ArrayList<Dan> dans = KhoiTao.KhoiTaoDan();
    public static ArrayList<OQuan> oQuans = KhoiTao.KhoiTaoOQuan();
    public static ArrayList<Quan> quans = KhoiTao.KhoiTaoQuan();




    public static void main(String[] args) {


        //Thêm Dân vào Ô Dân
        int danIndex = 0;
        for (int i = 0; i < oDans.size()  ; i++) {
            for (int j = 0; j < ODan.DEFAULT_STONES ; j++) {
                oDans.get(i).setDans(dans.get(danIndex));
                danIndex++;
            }


        }

        //Thêm Quan và 0 Dân vào Ô Quan
        oQuans.get(0).setQuan(quans.ge(0));
        oQuans.get(0).setDans(oQuans.get(0).getDans(), null);
        oQuans.get(1).setDans(oQuans.get(1).getDans(), null);

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

    private static void printBoard() {
        System.out.print("     ");
        for (int k = 9; k > 4; k--) {
            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " ");
        }
        System.out.println();
        System.out.println(OQuan.sumQuanAndDans(oQuans.get(1).getQuan(),oQuans.get(1).getDans()) + "," + "              " + OQuan.sumQuanAndDans(oQuans.get(0).getQuan(),oQuans.get(0).getDans()));
        System.out.print("     ");
        for (int k = 0; k < 5; k++) {
            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " ");
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
                scored01 += sumRange(0, 5);
                player1.setScore(scored01);
                scored02 += sumRange(6, 11);
                player2.setScore(scored02);
                if (scored01 < scored02) {
                    System.out.println("Player:" + player2.getName()  + ": Win!");
                } else if (scored02 < scored01) {
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

            ArrayList stones = oDans.get(hole).getDans() ;
            oDans.get(hole).clearDans();

            System.out.print("Chọn chiều Phải - Trái (p/t): ");
            String chieu = scanner.next();

            int i = chieu.equals("t") ? hole - 1 : hole + 1;
            phanphoi(stones, chieu, i);
            printBoard();
        }
    }

    private static void phanphoi(ArrayList stones, String chieu, int i) {
        if (chieu.equals("t")) {
            // Distribute stones to the left
            while (!stones.isEmpty()) {
                Dan dan_temp = (Dan) stones.getLast();
                if (i < 0) i = 11;
                if (i==5) {
                    oQuans.getFirst().setDans(oQuans.getFirst().getDans(), dan_temp);
                }
                else if (i==11) {
                    oQuans.get(1).setDans(oQuans.get(1).getDans(), dan_temp);
                }
                else {
                    oDans.get(i).setDans(dan_temp);
                }
                stones.remove(stones.size() - 1);
                i--;
                printBoard();
            }

            if (i < 0) i = 11;

            if (i == oQuans.get(0).getIndex()) {
                stones = oQuans.getFirst().getDans();
            }
            else if (i == oQuans.get(1).getIndex()) {
                stones = oQuans.get(1).getDans();
            }
            else {
                stones = oDans.get(i).getDans();

            }

            while (!stones.isEmpty()) {
//                if (i == 5 || i == 11) break;
                Dan dan_temp = (Dan) stones.get(stones.size() - 1); ////////////////////////////là gì nhỉ???????????????????????????????
                ArrayList stones_temp;
                if (i == oQuans.get(0).getIndex()) {
                    stones_temp = oQuans.getFirst().getDans();
                    oQuans.getFirst().clearDans();
                }
                else if (i == oQuans.get(1).getIndex()) {
                    stones_temp = oQuans.get(1).getDans();
                    oQuans.get(1).clearDans();
                }
                else {
                    stones_temp = oDans.get(i).getDans();
                    oDans.get(i).clearDans();
                }
//                int stones2 = board[i];
//                board[i] = 0;
                i--;
                while (stones_temp.size() > 0) {
                    if (i < 0) i = 11;
//                    Dan dan_temp = (Dan) stones.get(stones.size() - 1);
                    if (i==5) {
                        oQuans.getFirst().setDans(oQuans.getFirst().getDans(), dan_temp);
                    }
                    else if (i==11) {
                        oQuans.get(1).setDans(oQuans.get(1).getDans(), dan_temp);
                    }
                    else {
                        oDans.get(i).setDans(dan_temp);
                    }
                    stones_temp.remove(stones_temp.size() - 1);
                    i--;
                }
                if (stones_temp.size() == 0 && i == -1) i = 11;
                printBoard();
            }

            // Capture stones
//            int diemCong = 0;
//            if (i == oQuans.get(0).getIndex()){
//
//            }
//
//            while (board[i] == 0) {
//                if (i <= 0) i = 12;
//                if (board[i - 1] > 0) {
//                    if (currentPlayer == 0) scored01 += board[i - 1];
//                    if (currentPlayer == 1) scored02 += board[i - 1];
//                    diemCong += board[i - 1];
//                    board[i - 1] = 0;
//                    i--;
//                } else if (board[i - 1] == 0) {
//                    break;
//                }
////                if (i >= 11) i = -1;
//                if (i <= 0) i = 12;
//                i--;
////                printBoard();
//            }
//
//            printScore(diemCong);
//
//        } else if (chieu.equals("p")) {
//            // Distribute stones to the right
//            while (stones > 0) {
//                if (i > 11) i = 0;
//                board[i]++;
//                stones--;
//                i++;
////                printBoard();
//            }
//            if (i > 11) i = 0;
//
//            while (board[i] > 0) {
//                if (i == 5 || i == 11) break;
//                int stones2 = board[i];
//                board[i] = 0;
//                i++;
//                while (stones2 > 0) {
//                    if (i > 11) i = 0;
//                    board[i]++;
//                    stones2--;
//                    i++;
//                }
//                if (stones2 == 0 && i == 12) i = 0;
////                printBoard();
//            }
//
//            // Capture stones
//            int diemCong = 0;
//            while (board[i] == 0) {
//                if (i >= 11) i = -1;
//                if (board[i + 1] > 0) {
//                    if (currentPlayer == 0) scored01 += board[i + 1];
//                    if (currentPlayer == 1) scored02 += board[i + 1];
//                    diemCong += board[i + 1];
//                    board[i + 1] = 0;
//                    i++;
//                } else if (board[i + 1] == 0) {
//                    break;
//                }
////                if (i <= 0) i = 12;
//                if (i >= 11) i = -1;
//                i++;
////                printBoard();
//
//            }

//            printScore(diemCong);
        }

        currentPlayer++;
//        raithhem();
    }

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

    private static int sumRange(int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += oDans.get(i).sumDans(oDans.get(i).getDans());
        }
        return sum;
    }

    private static void printScore(int diemCong) {
        if (currentPlayer == 0) {
            System.out.println("Người chơi " + player1.getName() + " nhận được: " + diemCong);
            System.out.println("Điểm của " + player1.getName() + ": " + scored01);
        }
        if (currentPlayer == 1) {
            System.out.println("Người chơi " + player2.getName() + " nhận được: " + diemCong);
            System.out.println("Điểm của " + player2.getName() + ": " + scored02);
        }
    }

    private static void printFinalScore() {
        System.out.println("Điểm của " + player1.getName() + ": " + scored01);
        System.out.println("Điểm của " + player2.getName() + ": " + scored02);
        if (scored01 < scored02) {
            System.out.println(player1.getName() +": Win!");
        } else if (scored02 < scored01) {
            System.out.println(player2.getName() +": Win!");
        } else {
            System.out.println("Hòa");
        }
    }
}