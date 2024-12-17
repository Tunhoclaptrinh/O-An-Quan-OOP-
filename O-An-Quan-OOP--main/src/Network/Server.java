//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Network;

import Model.Da.*;
import Initialization.InitializationForTwo;
import Model.OCo.*;
import Model.Player.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static Network.Client2.countdown;

public class Server {
    public static Player player2 ;
    public static Player player1 ;
    static int currentPlayer;
    private static int count;
    private static Scanner scanner;
    public static InitializationForTwo init;
    public static ArrayList<ODan> oDans;
    public static ArrayList<Dan> dans;
    public static ArrayList<OQuan> oQuans;
    public static ArrayList<Quan> quans;
    public static boolean isPlayer2Connected = false;
    private static ServerSocket serverSocket;
    private static Socket player1Socket;
    private static Socket player2Socket;
    private static BufferedReader reader1; //Get dữ liệu từ người dùng 1
    private static BufferedReader reader2; //Get dữ liệu từ người dùng 2
    private static BufferedWriter writer1; //Post dữ liệu về Server
    private static BufferedWriter writer2; //Post dữ liệu về Server
    public static int currentStones;


    public Server() {
    }

    public static void Z() {

        try {
            InetAddress serverAddress = InetAddress.getByName("0.0.0.0"); // Địa chỉ IP của máy chủ trong mạng LAN
            serverSocket = new ServerSocket(12370, 2, serverAddress); //backlog => giới hạn 2 client  truy cập vào


            System.out.println("Server đang chờ người chơi...");

            player1Socket = serverSocket.accept();
            System.out.println("Người chơi 1 đã kết nối!");
            reader1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            writer1 = new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream()));
            String player1Name = reader1.readLine();  // Đọc tên người chơi từ client
            player1 = new Player(player1Name);
            writer1.write("Player1: " + player1.getName());
            writer1.newLine();
            writer1.flush();


            new Thread(() -> {
                try {
                    player2Socket = serverSocket.accept();  // Người chơi 2 kết nối
                    System.out.println("Người chơi 2 đã kết nối!");
                    reader2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
                    writer2 = new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream()));
                    String player2Name = reader2.readLine();
                    player2 = new Player(player2Name);  // Khởi tạo player2 khi kết nối
                    isPlayer2Connected = true;
                    writer2.write("Player2: " + player2.getName());
                    writer2.newLine();
                    writer2.flush();
                    writer2.write("Player1: " + player1.getName());
                    writer2.newLine();
                    writer2.flush();
                    try {
                        writer1.write("Player2: " + player2.getName());
                        writer1.newLine();
                        writer1.flush();
                        writer1.write("Game start");
                        writer1.newLine();
                        writer1.flush();
                        writer2.write("Game start");
                        writer2.newLine();
                        writer2.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    notifyPlayersGameStart();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Không thể kết nối với Player 2.");
                    SearchFailed();
                }
            }).start();
            while (countdown > 0 && !isPlayer2Connected) {
                writer1.write("Đang tìm đối thủ... " + countdown);
                writer1.newLine();
                writer1.flush();
                Thread.sleep(1000);
            }


            currentPlayer = 0;
            int danIndex = 0;
            for (int i = 0; i < oDans.size(); i++) {
                for (int j = 0; j < 5; j++) {
                    ((ODan) oDans.get(i)).setDans((Dan) dans.get(danIndex));
                    danIndex++;
                }
            }

            oDans.add(5, null);
            ((OQuan) oQuans.get(0)).setQuan((Quan) quans.get(0));
            ((OQuan) oQuans.get(1)).setQuan((Quan) quans.get(1));
            ((OQuan) oQuans.get(0)).setDans((Dan) null);
            ((OQuan) oQuans.get(1)).setDans((Dan) null);
            if (oDans == null || oQuans == null || oDans.size() < 11 || oQuans.size() < 2) {
                throw new IllegalStateException("Bàn cờ không hợp lệ. Kiểm tra lại việc khởi tạo!");
            }

            playGame();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static void sendScoresToClients() {
        try {
            if (player1 != null && player2 != null) {
                int player1Score = player1.sumQuanAndDans();
                int player2Score = player2.sumQuanAndDans();

                writer1.write("Player1Score: " + player1Score);
                writer1.newLine();
                writer1.flush();

                writer2.write("Player2Score: " + player2Score);
                writer2.newLine();
                writer2.flush();
            }
        } catch (IOException e) {
            System.err.println("Lỗi" + e.getMessage());
        }
    }

    private static void SearchFailed() {
        try {
            // Nếu không có đối thủ, thông báo cho Player 1
            writer1.write("Tìm kiếm thất bại!");
            writer1.newLine();
            writer1.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void notifyPlayersGameStart() {
        try {
            // Gửi thông báo game start cho cả hai người chơi
            writer1.write("Game start");
            writer1.newLine();
            writer1.flush();
            writer2.write("Game start");
            writer2.newLine();
            writer2.flush();
            System.out.println("Game start");

            playGame();
            scanner.close();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void printBoard() {
        StringBuilder board = new StringBuilder();
        board.append("     | ");

        for (int k = 10; k > 5; k--) {
            board.append(ODan.sumDans(oDans.get(k).getDans())).append(" | ");
        }
        board.append("\n");
        board.append("[").append(OQuan.sumQuanAndDans(oQuans.get(1).getQuan(), oQuans.get(1).getDans())).append(",").append(oQuans.get(1).getDans().size()).append("]                   [").append(oQuans.get(0).getDans().size()).append(",").append(OQuan.sumQuanAndDans(oQuans.get(0).getQuan(), oQuans.get(0).getDans())).append("]")
                .append("\n");
        board.append("     | ");

        for (int k = 0; k < 5; k++) {
            board.append(ODan.sumDans(oDans.get(k).getDans())).append(" | ");
        }
        board.append("\n");

        try {
            writer1.write(board.toString());
            writer1.newLine();
            writer1.flush();
            writer2.write(board.toString());
            writer2.newLine();
            writer2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(board);
    }

    static void playGame() {
        try {
            while (true) {
                if (currentPlayer == 2) {
                    currentPlayer = 0;
                }
                printBoard();
                if (OQuan.sumQuanAndDans(((OQuan) oQuans.get(0)).getQuan(), ((OQuan) oQuans.get(0)).getDans()) == 0) {
                    if (OQuan.sumQuanAndDans(((OQuan) oQuans.get(1)).getQuan(), ((OQuan) oQuans.get(1)).getDans()) == 0) {
                        player1.setDans(sumRange(0, 5));
                        player2.setDans(sumRange(6, 11));
                        printFinalScore();
                        break;
                    }
                }

                BufferedWriter currentWriter = currentPlayer == 0 ? writer1 : writer2;
                BufferedReader currentReader = currentPlayer == 0 ? reader1 : reader2;
                String var10001 = currentPlayer == 0 ? player1.getName() : player2.getName();
                currentWriter.write("Lượt của bạn (" + var10001 + "):");
                currentWriter.newLine();
                currentWriter.write("Chọn lỗ (0-4) <=> (6-10) : ");
                currentWriter.newLine();
                currentWriter.flush();
                int hole = Integer.parseInt(currentReader.readLine());
                if ((currentPlayer != 0 || hole >= 0 && hole <= 4) && (currentPlayer != 1 || hole >= 6 && hole <= 10)) {

                    if (ODan.sumDans(((ODan) oDans.get(hole)).getDans()) != 0) {
                        currentWriter.write("Chọn chiều Phải - Trái (p/t): ");
                        currentWriter.newLine();
                        currentWriter.flush();
                        String chieu = currentReader.readLine();
                        int i = chieu.equals("t") ? hole - 1 : hole + 1;
                        phanphoi(hole, chieu, i);
                        printBoard();

                        continue;
                    }
                }

                currentWriter.write("Lựa chọn không hợp lệ. Vui lòng chọn lại!\n");
                currentWriter.newLine();
                currentWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void phanphoi(int hole, String chieu, int i) throws InterruptedException {
        if (chieu.equals("t")) {

            // Distribute stones to the left
            ArrayList stones = (ArrayList) (oDans.get(hole).getDans()).clone();
            currentStones = stones.size();


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

                currentStones --;

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
                    currentStones = stones.size();
                    Thread.sleep(3000);

                }
                i--;

                printBoard();
                Thread.sleep(1000);

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
                Thread.sleep(1000);
            }

            printScore(luu_diemCong, luu_quanCong );

        } else if (chieu.equals("p")) {
            // Distribute stones to the right
            ArrayList stones = (ArrayList) (oDans.get(hole).getDans()).clone();
            currentStones = stones.size();

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

                currentStones --;


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
                    currentStones = stones.size();
                    Thread.sleep(3000);
                }
                i++;

                printBoard();
                Thread.sleep(1000);
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

                printBoard();
                Thread.sleep(1000);
            }

            printScore(luu_diemCong, luu_quanCong);
        }

        currentPlayer++;
        count ++;
        raithhem();
    }

    private static void raithhem() {
        if ((currentPlayer == player1.getPlayer_id() || currentPlayer == player1.getPlayer_id() + 2)
                && sumRange(0, 5).isEmpty()) {
            if (player1.getDans().size() == 0) {
                printFinalScore();
            }

            if (player1.getDans().size() > 0 && player1.getDans().size() <= 5) {
                ArrayList newStones = (ArrayList) player1.getDans().clone();
                player1.getDans().clear();

                for (int z = ((ODan) oDans.getFirst()).getIndex(); z < ((ODan) oDans.get(5)).getIndex(); z++) {
                    Dan newDan_temp = (Dan) newStones.getLast();
                    ((ODan) oDans.get(z)).setDans(newDan_temp);
                    newStones.removeLast();
                }
            }

            if (player1.getDans().size() > 5) {
                ArrayList newStones = new ArrayList();

                for (int i = 0; i < 5; i++) {
                    newStones.add(player1.getDans().get(i));
                    player1.getDans().remove(i);
                }

                for (int z = ((ODan) oDans.getFirst()).getIndex(); z <= ((ODan) oDans.get(4)).getIndex(); z++) {
                    Dan newDan_temp = (Dan) newStones.getLast();
                    ((ODan) oDans.get(z)).setDans(newDan_temp);
                    newStones.removeLast();
                }
            }
        }

        if (currentPlayer == player2.getPlayer_id() && sumRange(6, 11).isEmpty()) {
            if (player2.getDans().size() == 0) {
                printFinalScore();
            }

            if (player2.getDans().size() > 0 && player2.getDans().size() <= 5) {
                ArrayList newStones = (ArrayList) player2.getDans().clone();
                player2.getDans().clear();

                for (int z = ((ODan) oDans.get(6)).getIndex(); z <= ((ODan) oDans.getLast()).getIndex(); z++) {
                    Dan newDan_temp = (Dan) newStones.getLast();
                    ((ODan) oDans.get(z)).setDans(newDan_temp);
                    newStones.removeLast();
                }
            }

            if (player2.getDans().size() > 5) {
                ArrayList newStones = new ArrayList();

                for (int i = 0; i < 5; i++) {
                    newStones.add(player2.getDans().get(i));
                    player2.getDans().remove(i);
                }

                for (int z = ((ODan) oDans.get(6)).getIndex(); z <= ((ODan) oDans.getLast()).getIndex(); z++) {
                    Dan newDan_temp = (Dan) newStones.getLast();
                    ((ODan) oDans.get(z)).setDans(newDan_temp);
                    newStones.removeLast();
                }
            }
        }

    }

    private static ArrayList<Dan> sumRange(int start, int end) {
        ArrayList<Dan> sumRange = new ArrayList();

        for (int i = start; i < end; i++) {
            ArrayList<Dan> dans = (ArrayList) ((ODan) oDans.get(i)).getDans().clone();
            sumRange.addAll(dans);
            dans.clear();
            printBoard();
        }

        return sumRange;
    }

    public static void printScore(int luu_diemCong, int luu_quanCong) {
        try {
            if (currentPlayer == 0) {
                String message = "Người chơi " + player1.getName() + " nhận được: " + luu_diemCong + " Dân, "
                        + luu_quanCong + " Quan\n" +
                        "Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans() + "\n";
                writer1.write(message);
                writer1.newLine();
                writer1.flush();

                writer2.write(message);
                writer2.newLine();
                writer2.flush();
            }

            if (currentPlayer == 1) {
                String message = "Người chơi " + player2.getName() + " nhận được: " + luu_diemCong + " Dân, "
                        + luu_quanCong + " Quan\n" +
                        "Điểm của " + player2.getName() + ": " + player2.sumQuanAndDans() + "\n";
                writer1.write(message);
                writer1.newLine();
                writer1.flush();

                writer2.write(message);
                writer2.newLine();
                writer2.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFinalScore() {
        try {
            String player1Score = "Điểm của " + player1.getName() + ": " + player1.sumQuanAndDans() + "\n";
            String player2Score = "Điểm của " + player2.getName() + ": " + player2.sumQuanAndDans() + "\n";

            String result;
            if (player1.sumQuanAndDans() > player2.sumQuanAndDans()) {
                result = "Người chiến thắng: " + player1.getName() + "!\n";
            } else if (player2.sumQuanAndDans() > player1.sumQuanAndDans()) {
                result = "Người chiến thắng: " + player2.getName() + "!\n";
            } else {
                result = "Kết quả: Hòa!\n";
            }

            String finalMessage = player1Score + player2Score + result;

            writer1.write(finalMessage);
            writer1.newLine();
            writer1.flush();

            writer2.write(finalMessage);
            writer2.newLine();
            writer2.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
//        currentPlayer = player1.getPlayer_id();
        count = 2;
        scanner = new Scanner(System.in);
        init = new InitializationForTwo();
        oDans = init.InitODan();
        dans = init.InitDan();
        oQuans = init.InitOQuan();
        quans = init.InitQuan();
    }
}