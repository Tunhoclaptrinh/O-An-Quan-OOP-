//package Network;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class client {
//
//    public static void main(String[] args) {
//        try (Socket socket = new Socket("localhost", 12345)) {
//            System.out.println("Đã kết nối đến server!");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            Scanner scanner = new Scanner(System.in);
//            (new Thread(() -> {
//                String message;
//                try {
//                    while ((message = reader.readLine()) != null) {
//                        System.out.println(message);
//                    }
//                } catch (IOException var2) {
//                    System.err.println("Mất kết nối đến server.");
//                }
//
//            })).start();
//
//            String input;
//            do {
//                input = scanner.nextLine();
//                writer.write(input);
//                writer.newLine();
//                writer.flush();
//            } while (!input.equalsIgnoreCase("exit"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
package Network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static int port = 0;


    public static void setPort() {

        if (/*Server.port == 0*/ port == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter port: ");
            port = sc.nextInt();
            Server.port = port;
            new Server();
            sc.close();
        }
    }

    public static void main(String[] args) {
        setPort();

        // Địa chỉ IP và cổng của server
        String serverIp = "0.0.0.0"; // Cập nhật với địa chỉ IP server thực tế
        int serverPort = port; // Cổng kết nối

        try (Socket socket = new Socket(serverIp, serverPort)) {
            System.out.println("Đã kết nối đến server!");

            // Khởi tạo input/output stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);

            // Thread để lắng nghe thông điệp từ server
            new Thread(() -> {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println( message);
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
