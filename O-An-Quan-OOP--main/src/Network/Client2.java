package Network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import static Network.Play.startGame;

public class Client2 {
    private static String playerName = "";
    private static JFrame frame;
    private static JLabel countdownLabel;
    public static int countdown = 100;  // Đếm ngược 10 giây
    private static Timer timer;

    public static void setPlayerName(String name) {
        playerName = name;
    }

    public static void A() {
        // Địa chỉ IP của server
        String serverIp = "0.0.0.0";
        int serverPort = 12370;

        frame = new JFrame("Đang chờ người chơi...");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        countdownLabel = new JLabel("Đang chờ người chơi... " + countdown, SwingConstants.CENTER);
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(16f));
        frame.add(countdownLabel);
        frame.setVisible(true);

        try (Socket socket = new Socket(serverIp, serverPort)) {
            System.out.println("Đã kết nối đến server!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);

            if (!playerName.isEmpty()) {
                writer.write( playerName);
                writer.newLine();
                writer.flush();
                System.out.println("Đã gửi tên người chơi lên server: " + playerName);
            } else {
                System.out.println("Tên người chơi không hợp lệ.");
            }


            new Thread(() -> {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);

                        if ("Game start".equals(message)) {
                            startGame();
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Mất kết nối đến server.");
                }
            }).start();

            startCountdown();

            // Nhập lệnh từ người chơi và gửi lên server
            String input;
            do {
                input = scanner.nextLine();
                writer.write(input);
                writer.newLine();
                writer.flush();
            } while (!input.equalsIgnoreCase("exit"));

            System.out.println("Ngắt kết nối khỏi server.");
        } catch (IOException e) {
            System.err.println("Không thể kết nối đến server. Kiểm tra địa chỉ IP, cổng, hoặc trạng thái server.");
            e.printStackTrace();
        }
    }

    // Phương thức bắt đầu đếm ngược
    private static void startCountdown() {
        timer = new Timer(1000, e -> {
            if (countdown > 0) {
                countdownLabel.setText("Đang chờ người chơi... " + countdown);
                countdown--;
            } else {
                countdownLabel.setText("tìm kiếm thất bại");
            }
        });
        timer.start();
    }

    // Phương thức để chuyển sang màn hình game
    private static void startGame() {
        if (timer != null) {
            timer.stop(); // Dừng bộ đếm ngược nếu trò chơi bắt đầu
        }
        new ServerGUI();
        frame.dispose(); // Đóng cửa sổ chờ
        /*.setVisible(true);*/
    }
}

