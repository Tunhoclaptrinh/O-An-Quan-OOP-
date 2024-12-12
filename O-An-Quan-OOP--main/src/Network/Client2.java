package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    // Biến lưu trữ cổng kết nối và tên người chơi
    public static int port = 0;
    private static String playerName = "";  // Biến lưu tên người chơi

    // Phương thức để thiết lập tên người chơi
    public static void setPlayerName(String name) {
        playerName = name;  // Cập nhật tên người chơi
    }

    // Phương thức yêu cầu người dùng nhập cổng nếu chưa có cổng
    public static void setPort(int portInput) {
        if (port == 0 && portInput != 0) {
            port = portInput; // Thiết lập cổng kết nối từ giá trị được truyền vào
        }
    }

    // Phương thức chính của client
    public static void A() {
        // Địa chỉ IP của server
        String serverIp = "0.0.0.0";  // Địa chỉ IP của server, có thể thay đổi khi cần
        int serverPort = 12369;  // Cổng kết nối từ input của người dùng

        try (Socket socket = new Socket(serverIp, serverPort)) {
            System.out.println("Đã kết nối đến server!");

            // Khởi tạo input/output stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in); // Mở scanner ở đây để xử lý đầu vào từ người dùng

            // Gửi tên người chơi lên server
            if (!playerName.isEmpty()) {
                writer.write("Tên người chơi: " + playerName);
                writer.newLine();
                writer.flush();
                System.out.println("Đã gửi tên người chơi lên server: " + playerName);
            } else {
                System.out.println("Tên người chơi không hợp lệ.");
            }

            // Thread để lắng nghe thông điệp từ server
            new Thread(() -> {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Mất kết nối đến server.");
                }
            }).start();

            // Gửi thông điệp từ client tới server
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
}