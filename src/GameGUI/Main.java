//package GameGUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//import Player.Player;
//import Da.*;
//import OCo.*;
//
//import OCo.OQuan;
//import Player.Player;
//
//import java.util.Scanner;
//import java.util.Arrays;
//
//
//
//
//
//public class Main extends JFrame {
//    private ControlWindow cw = new ControlWindow();
//
//    public Main(){
//        this.add(cw);
//        this.pack();
//        this.setTitle("O An Quan");
//        this.setSize(Consts.WIDTH, Consts.HEIGHT);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//
//
//    }
//
//    public static void main(String[] args) {
//        new Main();
//    }
//}
//
//class ControlWindow extends JPanel implements ActionListener, KeyListener {
//
//    private Timer timer = new Timer(10, this);
//    static Test test = new Test();
//    private static Player player1 = test.player1;
//    private static Player player2 = test.player2;
//
//    private static int currentPlayer = 0;
//    private static int scored01 = player1.getScore();
//    private static int scored02 = player2.getScore();
//
//
//    // Initialize the board
//
//
//
//
//
//    private Font gameFont = new Font("Press Start 2P" , Font.PLAIN, 30);
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        // Set the stroke (line thickness) to 5 , set độ dày cho bàn cờ
//        g2d.setStroke(new BasicStroke(OCo.THICKNESS));
//
//        g.setColor(Color.WHITE); //set màu cho mọi thứ
//
//        g.setColor(Color.GREEN);
//        g.setFont(gameFont);
//
//        // Hiển thị tên người chơi
//        g.drawString("Player1: " + player1.getName(), 5, 40);
//        g.drawString( player2.getName() + " :Player2", Consts.WIDTH - 250 - 130, 40);
//        //Hiển thị điểm người chơi
//        g.drawString("Score: " + player1.getScore(), 5, 90);
//        g.drawString(player2.getScore() + "  : Score" , Consts.WIDTH - 250 - 70, 90);
//
//        // Vẽ Ô Quan
//        g.setColor(OQuan.oQuanColor);
//        g.drawOval(OQuan.x /*- ODan.WITH*/ , OQuan.y, 2*ODan.WIDTH, 2*ODan.HEIGHT);
//        g.drawOval(OQuan.x + 5*ODan.WIDTH , OQuan.y,2*ODan.WIDTH, 2*ODan.HEIGHT);
//        // vẽ đè lên hình tròn => tạo Ô Quan là một nửa hình tròn
//        g.setColor(Color.BLACK);
//        g.fillRect(OQuan.x + OQuan.WIDTH/2 , OQuan.y, 5*ODan.WIDTH ,2*ODan.HEIGHT);
//
//        //Vẽ Ô Dân
//        g.setColor(ODan.oDanColor);
//        for (int i = 1; i < 6; i++){
//            g.drawRect(OCo.x + i*ODan.WIDTH, OCo.y, ODan.WIDTH, ODan.HEIGHT);
//            g.drawRect(OCo.x + i*ODan.WIDTH,OCo.y + ODan.HEIGHT, ODan.WIDTH, ODan.HEIGHT);
//        }
//
//        //Vẽ điểm của Ô Dân
//        for (int i = 0; i < 5; i++){
//            g.drawString(  "" + test.board[i], OCo.x + OQuan.WIDTH/2 + i*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2 , OCo.y + 2*ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
//
//        }
//        for (int i = 10; i > 5; i--){
//            g.drawString(  "" + test.board[i], OCo.x + OQuan.WIDTH/2 + (i-6)*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2, OCo.y + ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
//        }
//
//        //Vẽ điểm ô Quan
//        g.drawString(  "" + test.board[11], OCo.x + OQuan.WIDTH/2 - ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y + OQuan.WIDTH/4 + (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan trái
//        g.drawString(  "" + test.board[5], OCo.x + OQuan.WIDTH/2  + 5*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y +  OQuan.WIDTH/4 +  (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan phải
//
//        // Set độ dày cho Chooser
//        g2d.setStroke(new BasicStroke(Chooser.THICKNESS));
//        // Set màu cho Chooser
//        g.setColor(Chooser.chooserColor);
//        // Vẽ Chooser
//        g.drawRect(Chooser.x, Chooser.y, Chooser.WIDTH, Chooser.HEIGHT);
//    }
//
//
//    @Override public void actionPerformed(ActionEvent e) {
//        Scanner sc = new Scanner(System.in);
//
//        OQuan quanP = new OQuan(5);
//        OQuan quanT = new OQuan(11);
////        test.board[quanP.getIndex()] = quanP.tinhDiem();
////        test.board[quanT.getIndex()] = quanT.tinhDiem();
////        test.playGame();
////        sc.close();
//        Test.nextTurn();
//
//        currentPlayer++;
//        repaint();
//    }
//
//
//    @Override public void keyPressed(KeyEvent e) {
//        //Nhấn vào là nhận rồi
//
//        //W,S
//        if (e.getKeyCode() == KeyEvent.VK_W) {
//
//        }
//        if (e.getKeyCode() == KeyEvent.VK_S) {
//
//        }
//
//        //Mũi tên LEN, XUONG
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//
//        }
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            Chooser.x += ODan.WIDTH;
//        }
//        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            Chooser.x += ODan.HEIGHT;
//        }
//    }
//
//    @Override public void keyReleased(KeyEvent e) {
//        //Chờ thả mới hoạt động
//    }
//
//    @Override public void keyTyped(KeyEvent e) {
//        //Nhấn vào thả ra mới nhận
//    }
//
//    public ControlWindow(){
//        timer.start();
//        this.setBackground(Color.BLACK);
//        this.addKeyListener(this);
//        this.setFocusable(true);
//    }
//}
//
//class Test {
//
//    public static Player player1 = new Player("Tuan");
//    public static Player player2 = new Player("Tun");
//    public static OQuan quanL = new OQuan(5);
//    public static OQuan quanR = new OQuan(11);
//
//    static int[] board = {5,5,5,5,5,10,5,5,5,5,5,10};
//
//    private static int currentPlayer = 0;
//    private static int scored01 = player1.getScore();
//    private static int scored02 = player2.getScore();
//    private static int count = 0; //đếm người chơi => Xoay vòng chơi
//    private static Scanner scanner = new Scanner(System.in);
//
//    private static void printBoard() {
//        System.out.print("     ");
//        for (int k = 10; k > 5; k--) {
//            System.out.print(board[k] + " ");
//        }
//        System.out.println();
//        System.out.println(board[11] + "              " + board[5]);
//        System.out.print("     ");
//        for (int k = 0; k < 5; k++) {
//            System.out.print(board[k] + " ");
//        }
//        System.out.println("\n");
//    }
//
//
//
//    public static void nextTurn() {
//        printBoard();
//        count++;
//        if (currentPlayer == 2) {
//            currentPlayer = 0;
//        }
//
//        if (board[5] == 0 && board[11] == 0) {
//            scored01 += sumRange(0, 5);
//            scored02 += sumRange(6, 11);
//            printFinalScore();
//            return;
//        }
//
//        int hole;
//        Scanner scanner = new Scanner(System.in);
//        if (currentPlayer == 0) {
//            System.out.println("Player1: " + player1.getName());
//            System.out.print("Chọn lỗ (0-4): ");
//            hole = scanner.nextInt();
//            if (hole == 5 || hole == 11 || board[hole] == 0) {
//                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
//                return;
//            }
//        } else {
//            System.out.println("Player2: " + player2.getName());
//            System.out.print("Chọn lỗ (6-10): ");
//            hole = scanner.nextInt();
//            if (hole == 5 || hole == 11 || board[hole] == 0) {
//                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
//                return;
//            }
//        }
//
//        int stones = board[hole];
//        board[hole] = 0;
//
//        System.out.print("Chọn chiều (p/t): ");
//        String chieu = scanner.next();
//
//        int i = chieu.equals("t") ? hole - 1 : hole + 1;
//        phanphoi(stones, chieu, i);
//
//
//        raithhem();
//    }
//
//
//    private static void phanphoi(int stones, String chieu, int i) {
//        if (chieu.equals("t")) {
//            // Distribute stones to the left
//            while (stones > 0) {
//                if (i < 0) i = 11;
//                board[i]++;
//                stones--;
//                i--;
////                printBoard();
//            }
//            if (i < 0) i = 11;
//
//            while (board[i] > 0) {
//                if (i == 5 || i == 11) break;
//                int stones2 = board[i];
//                board[i] = 0;
//                i--;
//                while (stones2 > 0) {
//                    if (i < 0) i = 11;
//                    board[i]++;
//                    stones2--;
//                    i--;
//                }
//                if (stones2 == 0 && i == -1) i = 11;
////                printBoard();
//            }
//
//            // Capture stones
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
//
//            printScore(diemCong);
//        }
//
//        currentPlayer++;
//        raithhem();
//    }
//
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
//
//    private static int sumRange(int start, int end) {
//        int sum = 0;
//        for (int i = start; i < end; i++) {
//            sum += board[i];
//        }
//        return sum;
//    }
//
//    private static void printScore(int diemCong) {
//        if (currentPlayer == 0) {
//            System.out.println("Người chơi " + player1.getName() + " nhận được: " + diemCong);
//            System.out.println("Điểm của " + player1.getName() + ": " + scored01);
//            player1.setScore(player1.getScore() + diemCong);
//        }
//        if (currentPlayer == 1) {
//            System.out.println("Người chơi " + player2.getName() + " nhận được: " + diemCong);
//            System.out.println("Điểm của " + player2.getName() + ": " + scored02);
//            player2.setScore(player2.getScore() + diemCong);
//        }
//    }
//
//    private static void printFinalScore() {
//        System.out.println("Điểm của " + player1.getName() + ": " + scored01);
//        System.out.println("Điểm của " + player2.getName() + ": " + scored02);
//        if (scored01 < scored02) {
//            System.out.println(player1.getName() +": Win!");
//        } else if (scored02 < scored01) {
//            System.out.println(player2.getName() +": Win!");
//        } else {
//            System.out.println("Hòa");
//        }
//    }
//}