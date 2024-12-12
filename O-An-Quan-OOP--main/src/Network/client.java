package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

    // Biến lưu trữ cổng kết nối
    public static int port = 0;

    // Phương thức yêu cầu người dùng nhập cổng nếu chưa có cổng
    public static void setPort() {
        if (port == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập cổng kết nối: ");
            port = sc.nextInt();
            // Không đóng Scanner tại đây vì sẽ dùng cho input từ người dùng tiếp theo
        }
    }

    // Phương thức chính của client
    public static void main(String[] args) {
        // Thiết lập cổng kết nối
        setPort();

        // Địa chỉ IP của server
        String serverIp = "0.0.0.0";  // Địa chỉ IP của server, có thể thay đổi khi cần
        int serverPort = port;  // Cổng kết nối từ input của người dùng

        try (Socket socket = new Socket(serverIp, serverPort)) {
            System.out.println("Đã kết nối đến server!");

            // Khởi tạo input/output stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in); // Mở scanner ở đây để xử lý đầu vào từ người dùng

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