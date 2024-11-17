//package GameBoard;
//
//import Da.Dan;
//import Da.Quan;
//import KhoiTao.KhoiTao;
//import OCo.ODan;
//import OCo.OQuan;
//import Player.Player;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Test3 {
//    public static Player player1 = new Player("Tuấn");
//    public static Player player2 = new Player("Tún");
//
//    private static int currentPlayer = 0;
//    private static int scored01 = player1.getScore();
//    private static int scored02 = player2.getScore();
//    private static int count = 0;
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static ArrayList<Object> gameBoard = new ArrayList<>();
//    public static ArrayList<ODan> oDans = KhoiTao.KhoiTaoODan();
//    public static ArrayList<Dan> dans = KhoiTao.KhoiTaoDan();
//    public static ArrayList<OQuan> oQuans = KhoiTao.KhoiTaoOQuan();
//    public static ArrayList<Quan> quans = KhoiTao.KhoiTaoQuan();
//
//    public static void main(String[] args) {
//        initializeGame();
//        playGame();
//        scanner.close();
//    }
//
//    private static void initializeGame() {
//        // Initialize ODan with Dans
//        int danIndex = 0;
//        for (ODan oDan : oDans) {
//            for (int j = 0; j < ODan.DEFAULT_STONES; j++) {
//                oDan.setDans(oDan.getDans(), dans.get(danIndex++));
//            }
//        }
//
//        // Initialize OQuan with Quans
//        for (int i = 0; i < oQuans.size(); i++) {
//            oQuans.get(i).setQuan(new ArrayList<>());
//            oQuans.get(i).getQuan().add(quans.get(i));
//        }
//
//        // Add all pieces to gameBoard
//        for (int i = 0; i < 12; i++) {
//            if (i == 5) {
//                gameBoard.add(oQuans.get(0));
//            } else if (i == 11) {
//                gameBoard.add(oQuans.get(1));
//            } else {
//                gameBoard.add(oDans.get(i < 5 ? i : i - 1));
//            }
//        }
//    }
//
//    private static void printBoard() {
//        System.out.print("     ");
//        for (int k = 9; k > 4; k--) {
//            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " ");
//        }
//        System.out.println();
//        System.out.println(OQuan.Interface.sumQuanAndDans(oQuans.get(1).getQuan(), oQuans.get(1).getDans())
//                + "              "
//                + OQuan.Interface.sumQuanAndDans(oQuans.get(0).getQuan(), oQuans.get(0).getDans()));
//        System.out.print("     ");
//        for (int k = 0; k < 5; k++) {
//            System.out.print(ODan.sumDans(oDans.get(k).getDans()) + " ");
//        }
//        System.out.println("\n");
//    }
//
//    private static void playGame() {
//        while (true) {
//            count++;
//            if (currentPlayer == 2) currentPlayer = 0;
//
//            printBoard();
//
//            // Check end game condition
//            if (isGameOver()) {
//                calculateFinalScores();
//                break;
//            }
//
//            // Get player move
//            int hole = getPlayerMove();
//            if (hole == -1) continue;
//
//            // Get stones from selected hole
//            ArrayList<Dan> stones = new ArrayList<>(oDans.get(hole).getDans());
//            oDans.get(hole).clearDans();
//
//            // Get direction of play
//            System.out.print("Chọn chiều Phải - Trái (p/t): ");
//            String direction = scanner.next().toLowerCase();
//
//            // Distribute stones
//            int nextPosition = direction.equals("t") ? hole - 1 : hole + 1;
//            distributeStones(stones, direction, nextPosition);
//
//            currentPlayer++;
//            raiThem();
//        }
//    }
//
//    private static boolean isGameOver() {
//        return OQuan.Interface.sumQuanAndDans(oQuans.get(0).getQuan(), oQuans.get(0).getDans()) == 0
//                && OQuan.Interface.sumQuanAndDans(oQuans.get(1).getQuan(), oQuans.get(1).getDans()) == 0;
//    }
//
//    private static int getPlayerMove() {
//        int hole;
//        if (currentPlayer == 0) {
//            System.out.println("Player1: " + player1.getName());
//            System.out.print("Chọn lỗ (0-4) <=> (1-5): ");
//            hole = scanner.nextInt();
//            if (hole < 0 || hole > 4 || ODan.sumDans(oDans.get(hole).getDans()) == 0) {
//                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
//                return -1;
//            }
//        } else {
//            System.out.println("Player2: " + player2.getName());
//            System.out.print("Chọn lỗ (6-10) <=> (1-5): ");
//            hole = scanner.nextInt();
//            if (hole < 6 || hole > 10 || ODan.sumDans(oDans.get(hole).getDans()) == 0) {
//                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
//                return -1;
//            }
//        }
//        return hole;
//    }
//
//    private static void distributeStones(ArrayList<Dan> stones, String direction, int position) {
//        if (direction.equals("t")) {
//            distributeLeft(stones, position);
//        } else {
//            distributeRight(stones, position);
//        }
//    }
//
//    private static void distributeLeft(ArrayList<Dan> stones, int position) {
//        // Distribute initial stones
//        while (!stones.isEmpty()) {
//            position = position < 0 ? 11 : position;
//            Dan stone = stones.remove(stones.size() - 1);
//
//            if (position == 5) {
//                oQuans.get(0).getDans().add(stone);
//            } else if (position == 11) {
//                oQuans.get(1).getDans().add(stone);
//            } else {
//                if (position == 10) position--;
//                oDans.get(position).getDans().add(stone);
//                position++;
//            }
//            position--;
//        }
//
//        // Continue distribution and capture
//        position = position < 0 ? 11 : position;
//        while (true) {
//            ArrayList<Dan> currentStones;
//            if (position == 5) {
//                if (oQuans.get(0).getDans().isEmpty()) break;
//                currentStones = new ArrayList<>(oQuans.get(0).getDans());
//                oQuans.get(0).clearDans();
//            } else if (position == 11) {
//                if (oQuans.get(1).getDans().isEmpty()) break;
//                currentStones = new ArrayList<>(oQuans.get(1).getDans());
//                oQuans.get(1).clearDans();
//            } else {
//                if (oDans.get(position).getDans().isEmpty()) {
//                    captureStones(position, "left");
//                    break;
//                }
//                currentStones = new ArrayList<>(oDans.get(position).getDans());
//                oDans.get(position).clearDans();
//            }
//            distributeLeft(currentStones, position - 1);
//            break;
//        }
//    }
//
//    private static void distributeRight(ArrayList<Dan> stones, int position) {
//        // Similar to distributeLeft but moving right
//        while (!stones.isEmpty()) {
//            position = position > 11 ? 0 : position;
//            Dan stone = stones.remove(stones.size() - 1);
//
//            if (position == 5) {
//                oQuans.get(0).getDans().add(stone);
//            } else if (position == 11) {
//                oQuans.get(1).getDans().add(stone);
//            } else {
//                oDans.get(position).getDans().add(stone);
//            }
//            position++;
//        }
//
//        position = position > 11 ? 0 : position;
//        while (true) {
//            ArrayList<Dan> currentStones;
//            if (position == 5) {
//                if (oQuans.get(0).getDans().isEmpty()) break;
//                currentStones = new ArrayList<>(oQuans.get(0).getDans());
//                oQuans.get(0).clearDans();
//            } else if (position == 11) {
//                if (oQuans.get(1).getDans().isEmpty()) break;
//                currentStones = new ArrayList<>(oQuans.get(1).getDans());
//                oQuans.get(1).clearDans();
//            } else {
//                if (oDans.get(position).getDans().isEmpty()) {
//                    captureStones(position, "right");
//                    break;
//                }
//                currentStones = new ArrayList<>(oDans.get(position).getDans());
//                oDans.get(position).clearDans();
//            }
//            distributeRight(currentStones, position + 1);
//            break;
//        }
//    }
//
//    private static void captureStones(int position, String direction) {
//        int nextPosition = direction.equals("left") ? position - 1 : position + 1;
//        if (direction.equals("left")) {
//            while (true) {
//                nextPosition = nextPosition < 0 ? 11 : nextPosition;
//                if (nextPosition == 5 || nextPosition == 11) break;
//
//                ODan nextHole = oDans.get(nextPosition);
//                if (nextHole.getDans().isEmpty()) break;
//
//                int capturedStones = nextHole.getDans().size();
//                if (currentPlayer == 0) {
//                    scored01 += capturedStones;
//                } else {
//                    scored02 += capturedStones;
//                }
//                nextHole.clearDans();
//                nextPosition--;
//            }
//        } else {
//            while (true) {
//                nextPosition = nextPosition > 11 ? 0 : nextPosition;
//                if (nextPosition == 5 || nextPosition == 11) break;
//
//                ODan nextHole = oDans.get(nextPosition);
//                if (nextHole.getDans().isEmpty()) break;
//
//                int capturedStones = nextHole.getDans().size();
//                if (currentPlayer == 0) {
//                    scored01 += capturedStones;
//                } else {
//                    scored02 += capturedStones;
//                }
//                nextHole.clearDans();
//                nextPosition++;
//            }
//        }
//    }
//
//    private static void raiThem() {
//        if ((currentPlayer == 0 || currentPlayer == 2) && sumRange(0, 5) == 0) {
//            handleEmptySide(true);
//        } else if (currentPlayer == 1 && sumRange(6, 11) == 0) {
//            handleEmptySide(false);
//        }
//    }
//
//    private static void handleEmptySide(boolean isFirstPlayer) {
//        int score = isFirstPlayer ? scored01 : scored02;
//        int startPos = isFirstPlayer ? 0 : 6;
//        int endPos = isFirstPlayer ? 5 : 11;
//
//        if (score == 0) {
//            calculateFinalScores();
//        } else if (score <= 5) {
//            distributeRemainingStones(startPos, endPos, score);
//            if (isFirstPlayer) scored01 = 0; else scored02 = 0;
//        } else {
//            distributeRemainingStones(startPos, endPos, 5);
//            if (isFirstPlayer) scored01 -= 5; else scored02 -= 5;
//        }
//    }
//
//    private static void distributeRemainingStones(int start, int end, int stones) {
//        for (int i = start; i < end && stones > 0; i++) {
//            Dan newDan = new Dan(i);
//            oDans.get(i).getDans().add(newDan);
//            stones--;
//        }
//    }
//
//    private static int sumRange(int start, int end) {
//        int sum = 0;
//        for (int i = start; i < end; i++) {
//            sum += ODan.sumDans(oDans.get(i).getDans());
//        }
//        return sum;
//    }
//
//    private static void calculateFinalScores() {
//        scored01 += sumRange(0, 5);
//        scored02 += sumRange(6, 11);
//        player1.setScore(scored01);
//        player2.setScore(scored02);
//        printFinalScore();
//    }
//
//    private static void printFinalScore() {
//        System.out.println("Điểm của " + player1.getName() + ": " + scored01);
//        System.out.println("Điểm của " + player2.getName() + ": " + scored02);
//        if (scored01 > scored02) {
//            System.out.println(player1.getName() + ": Win!");
//        } else if (scored02 > scored01) {
//            System.out.println(player2.getName() + ": Win!");
//        } else {
//            System.out.println("Hòa");
//        }
//    }
//}