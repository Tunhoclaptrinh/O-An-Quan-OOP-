package GameBoard;

import OCo.OQuan;
import Player.Player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Test {

    public static Player player1 = new Player("Tuấn");
    public static Player player2 = new Player("Tún");

    private static ArrayList gameBoard = new ArrayList();
    private static int[] board = new int[12];
    private static int currentPlayer = 0;
    private static int scored01 = player1.getScore();
    private static int scored02 = player2.getScore();
    private static int count = 0; //đếm người chơi => Xoay vòng chơi
    private static Scanner scanner = new Scanner(System.in);




    public static void main(String[] args) {
        // Initialize the board
        Arrays.fill(board, 5);
        OQuan quanP = new OQuan(5);
        OQuan quanT = new OQuan(11);

        playGame();
        scanner.close();
    }

    private static void printBoard() {
        System.out.print("     ");
        for (int k = 10; k > 5; k--) {
            System.out.print(board[k] + " ");
        }
        System.out.println();
        System.out.println(board[11] + "              " + board[5]);
        System.out.print("     ");
        for (int k = 0; k < 5; k++) {
            System.out.print(board[k] + " ");
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

            if (board[5] == 0 && board[11] == 0) {
                scored01 += sumRange(0, 5);
                scored02 += sumRange(6, 11);
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
                if (hole == 5 || hole == 11 || board[hole] == 0) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    continue;
                }
            } else {
                System.out.println("Player2: " + player2.getName());
                System.out.print("Chọn lỗ (6-10) <=> (1-5): ");
                hole = scanner.nextInt();
                if (hole == 5 || hole == 11 || board[hole] == 0) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    continue;
                }
            }

            int stones = board[hole];
            board[hole] = 0;

            System.out.print("Chọn chiều Phải - Trái (p/t): ");
            String chieu = scanner.next();

            int i = chieu.equals("t") ? hole - 1 : hole + 1;
            phanphoi(stones, chieu, i);
//            printBoard();
        }
    }

    private static void phanphoi(int stones, String chieu, int i) {
        if (chieu.equals("t")) {
            // Distribute stones to the left
            while (stones > 0) {
                if (i < 0) i = 11;
                board[i]++;
                stones--;
                i--;
//                printBoard();
            }
            if (i < 0) i = 11;

            while (board[i] > 0) {
                if (i == 5 || i == 11) break;
                int stones2 = board[i];
                board[i] = 0;
                i--;
                while (stones2 > 0) {
                    if (i < 0) i = 11;
                    board[i]++;
                    stones2--;
                    i--;
                }
                if (stones2 == 0 && i == -1) i = 11;
//                printBoard();
            }

            // Capture stones
//            int diemCong = 0;
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

//            printScore(diemCong);

        } else if (chieu.equals("p")) {
            // Distribute stones to the right
            while (stones > 0) {
                if (i > 11) i = 0;
                board[i]++;
                stones--;
                i++;
//                printBoard();
            }
            if (i > 11) i = 0;

            while (board[i] > 0) {
                if (i == 5 || i == 11) break;
                int stones2 = board[i];
                board[i] = 0;
                i++;
                while (stones2 > 0) {
                    if (i > 11) i = 0;
                    board[i]++;
                    stones2--;
                    i++;
                }
                if (stones2 == 0 && i == 12) i = 0;
//                printBoard();
            }

            // Capture stones
            int diemCong = 0;
            while (board[i] == 0) {
                if (i >= 11) i = -1;
                if (board[i + 1] > 0) {
                    if (currentPlayer == 0) scored01 += board[i + 1];
                    if (currentPlayer == 1) scored02 += board[i + 1];
                    diemCong += board[i + 1];
                    board[i + 1] = 0;
                    i++;
                } else if (board[i + 1] == 0) {
                    break;
                }
//                if (i <= 0) i = 12;
                if (i >= 11) i = -1;
                i++;
//                printBoard();

            }

            printScore(diemCong);
        }

        currentPlayer++;
        raithhem();
    }

    private static void raithhem() {
        if (currentPlayer == 0 || currentPlayer == 2) {
            if (sumRange(0, 5) == 0) {
                if (scored01 == 0) {
                    printFinalScore();
                }
                if (scored01 > 0 && scored01 <= 5) {
                    int newStones = scored01;
                    scored01 = 0;
                    for (int z = 0; z < 5; z++) {
                        while (newStones > 0) {
                            board[z]++;
                            newStones--;
                        }
                    }
                }
                if (scored01 > 5) {
                    scored01 -= 5;
                    Arrays.fill(board, 0, 5, 1);
                }
            }
        }

        if (currentPlayer == 1) {
            if (sumRange(6, 11) == 0) {
                if (scored02 == 0) {
                    printFinalScore();
                }
                if (scored02 > 0 && scored02 <= 5) {
                    int newStones = scored02;
                    scored02 = 0;
                    for (int z = 6; z < 11; z++) {
                        while (newStones > 0) {
                            board[z]++;
                            newStones--;
                        }
                    }
                }
                if (scored02 > 5) {
                    scored02 -= 5;
                    Arrays.fill(board, 6, 11, 1);
                }
            }
        }
    }

    private static int sumRange(int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += board[i];
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