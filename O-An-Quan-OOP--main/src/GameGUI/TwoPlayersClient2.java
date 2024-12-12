package GameGUI;

import Network.Client2;
import Network.Play;

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

        JLabel nameLabel = new JLabel("Enter Your Gate:");
        nameLabel.setBounds(Consts.WIDTH / 2 - 220, Consts.HEIGHT / 3 - 30, 800, 50);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Press Start 2P", Font.BOLD, 30));

        JTextField nameField = new JTextField();
        nameField.setBounds(Consts.WIDTH / 2 - (Consts.WIDTH * 3) / 10, Consts.HEIGHT / 3 + 30, (Consts.WIDTH * 3) / 5, (Consts.HEIGHT / 10));
        nameField.setFont(new Font("Press Start 2P", Font.PLAIN, 30));

        // Nút Submit
        ImageIcon playIcon = new ImageIcon(getClass().getResource("/assets/Startbutton.png"));
        Image imgButton = playIcon.getImage().getScaledInstance(100, 30, Image.SCALE_SMOOTH);
        JButton submitButton = new JButton(new ImageIcon(imgButton));
        submitButton.setBounds(Consts.WIDTH / 2 - 50, Consts.HEIGHT / 2 + 20, 100, 30);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);

        submitButton.addActionListener(e -> new Thread(() -> {
            Client2 n = new Client2();
            n.A();
        }).start());

        // Nút Create Room
        JButton createRoomButton = new JButton("Create");
        createRoomButton.setBounds(Consts.WIDTH / 2 - 300, Consts.HEIGHT / 2 + 200, 200, 30);
        createRoomButton.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        createRoomButton.setBackground(Color.GREEN); // Màu xanh lá
        createRoomButton.setOpaque(true);            // Bắt buộc để hiện màu nền
        createRoomButton.setContentAreaFilled(true); // Hiển thị vùng nền
        createRoomButton.setBorderPainted(false);    // Ẩn viền nút nếu muốn
        createRoomButton.addActionListener(e -> new Thread(() -> {
            Play p = new Play();
            Play.startGame();
        }).start());

        // Thêm các thành phần vào JFrame
        add(nameLabel);
        add(nameField);
        add(submitButton);
        add(createRoomButton);
        add(backButton);
        add(background);

        this.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwoPlayersClient2::new);
    }
}