package GameGUI;

import Network.Client2;
import Network.Play;
import Network.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TwoPlayersClient2 extends JFrame {

    public TwoPlayersClient2() {
        // Thiết lập JFrame
        this.setTitle("Nhập thông tin người dùng");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null); // Tùy chỉnh vị trí các thành phần
        this.setLocationRelativeTo(null);

        // Thêm WindowListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        TwoPlayersClient2.this,
                        "Bạn có chắc muốn thoát không?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Thoát chương trình
                }
            }
        });

        // Thêm hình ảnh nền
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/background.png"));
        Image img = icon.getImage().getScaledInstance(Consts.WIDTH, Consts.HEIGHT, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);

        // Nút "Back"
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 70, 35);
        backButton.setFont(new Font("Press Start 2P", Font.BOLD, 8));
        backButton.setBackground(Color.GREEN);
        backButton.setForeground(Color.BLACK);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            SwingUtilities.invokeLater(GameModeWindow::new);
            dispose();
        });


        JButton joinRoomButton = new JButton("Join");
        joinRoomButton.setBounds(Consts.WIDTH / 2 , Consts.HEIGHT / 3 + 50, 200, 30);
        joinRoomButton.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        joinRoomButton.setBackground(Color.GREEN); // Màu xanh lá
        joinRoomButton.setOpaque(true);            // Bắt buộc để hiện màu nền
        joinRoomButton.setContentAreaFilled(true); // Hiển thị vùng nền
        joinRoomButton.setBorderPainted(true);    // Ẩn viền nút nếu muốn

        joinRoomButton.addActionListener(e -> new Thread(() -> {
            Client2 n = new Client2();
            n.A();
        }).start());


        JButton createRoomButton = new JButton("Create");
        createRoomButton.setBounds(Consts.WIDTH / 2 - 220, Consts.HEIGHT / 3 + 50, 200, 30);
        createRoomButton.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        createRoomButton.setBackground(Color.GREEN); // Màu xanh lá
        createRoomButton.setOpaque(true);            // Bắt buộc để hiện màu nền
        createRoomButton.setContentAreaFilled(true); // Hiển thị vùng nền
        createRoomButton.setBorderPainted(true);    // Ẩn viền nút nếu muốn
        createRoomButton.addActionListener(e -> new Thread(() -> {
            JOptionPane.showMessageDialog(null,"Server is running in port 12370");
            Server s =new Server();
            Server.Z();
        }).start());

        add(joinRoomButton);
        add(createRoomButton);
        add(backButton);
        add(background);

        this.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwoPlayersClient2::new);
    }
}