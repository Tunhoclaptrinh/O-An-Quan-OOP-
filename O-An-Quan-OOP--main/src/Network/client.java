//package Network;
//
//import GameControler.Test_LOGIC;
//import GameGUI.TestGUI;
//
//import javax.swing.*;
//import java.io.*;
//import java.net.Socket;
//import java.util.Scanner;
//
//
//
//public class client {
//
//    private static String playerName = "";
//    private static JFrame frame;
//    private static JLabel countdownLabel;
//    public static int countdown = 100;  // Đếm ngược 10 giây
//    private static Timer timer;
//
//    public static void setPlayerName(String name) {
//        playerName = name;
//    }
//
//    public static void A() {
//        // Địa chỉ IP của server
//        String serverIp = "0.0.0.0";  // Địa chỉ IP của server, có thể thay đổi khi cần
//        int serverPort = 12369;  // Cổng kết nối từ input của người dùng
//
//        // Khởi tạo giao diện người dùng với thông báo đếm ngược
//        frame = new JFrame("Đang chờ người chơi...");
//        frame.setSize(300, 150);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);  // Đặt cửa sổ ở giữa màn hình
//        countdownLabel = new JLabel("Đang chờ người chơi... " + countdown, SwingConstants.CENTER);
//        countdownLabel.setFont(countdownLabel.getFont().deriveFont(16f));
//        frame.add(countdownLabel);
//        frame.setVisible(true);
//
//        try (Socket socket = new Socket(serverIp, serverPort)) {
//            System.out.println("Đã kết nối đến server!");
//
//            // Khởi tạo input/output stream
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            Scanner scanner = new Scanner(System.in); // Mở scanner ở đây để xử lý đầu vào từ người dùng
//
//            // Gửi tên người chơi lên server
//            if (!playerName.isEmpty()) {
//                writer.write( playerName);
//                writer.newLine();
//                writer.flush();
//                System.out.println("Đã gửi tên người chơi lên server: " + playerName);
//            } else {
//                System.out.println("Tên người chơi không hợp lệ.");
//            }
//
//            // Thread để lắng nghe thông điệp từ server
//            new Thread(() -> {
//                String message;
//                try {
//                    while ((message = reader.readLine()) != null) {
//                        System.out.println(message);
//
//                        // Nếu server thông báo có đủ 2 người chơi, bắt đầu trò chơi
//                        if ("Game start".equals(message)) {
//                            countdownLabel.setText("Trò chơi bắt đầu!");
//                            startGame();
//                        }
//                    }
//                } catch (IOException e) {
//                    System.err.println("Mất kết nối đến server.");
//                }
//            }).start();
//
//            // Đếm ngược 10 giây khi chờ người chơi
//            startCountdown();
//
//            // Nhập lệnh từ người chơi và gửi lên server
//            String input;
//            do {
//                input = scanner.nextLine();
//                writer.write(input);
//                writer.newLine();
//                writer.flush();
//            } while (!input.equalsIgnoreCase("exit"));
//
//            System.out.println("Ngắt kết nối khỏi server.");
//        } catch (IOException e) {
//            System.err.println("Không thể kết nối đến server. Kiểm tra địa chỉ IP, cổng, hoặc trạng thái server.");
//            e.printStackTrace();
//        }
//    }
//
//    // Phương thức bắt đầu đếm ngược
//    private static void startCountdown() {
//        timer = new Timer(1000, e -> {
//            if (countdown > 0) {
//                countdownLabel.setText("Đang chờ người chơi... " + countdown);
//                countdown--;
//            } else {
////                if (!isPlayer2Connected==false){
////                    countdownLabel.setText("Trò chơi bắt đầu!");
////                    startGame();
////                    timer.stop();}
////                else{
//                countdownLabel.setText("tìm kiếm thất bại");
////                }
//                // Khi đếm ngược xong, chuyển đến trò chơi
//
//            }
//        });
//        timer.start();
//    }
//
//    // Phương thức để chuyển sang màn hình game
//    private static void startGame() {
//        // Đóng cửa sổ chờ
//        frame.dispose();
////        ClientGUI2 game = new ClientGUI2();
////        game.setVisible(true);
//
//        // Mở màn hình game (ví dụ: màn hình GUI của trò chơi)
////        TestGUI gameGUI = new TestGUI();
////        gameGUI.setVisible(true);àddddddddddddddddddddddddddddddddđ
//    }
//
////    public static void main(String[] args) {
////        // Test client
////        setPlayerName("Player1");
////        A();
////    }
//}
