package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TwoPlayersClient extends JFrame {

    public TwoPlayersClient() {
        // Thiết lập JFrame
        this.setTitle("Nhập thông tin người dùng");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null); // Tùy chỉnh vị trí các thành phần
        this.setLocationRelativeTo(null);

        // Thêm WindowListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Kiểm tra nếu StartMenu tồn tại
                SwingUtilities.invokeLater(GameModeWindow::new);
                dispose();
            }
        });

        // Thêm hình ảnh nền
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/background.png"));
        Image img = icon.getImage().getScaledInstance(Consts.WIDTH, Consts.HEIGHT, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);

        JLabel nameLabel = new JLabel("Enter Your Gate:");
        nameLabel.setBounds(Consts.WIDTH / 2 - 220, Consts.HEIGHT / 3 - 30, 800, 50);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Press Start 2P", Font.BOLD, 30));

        JTextField nameField = new JTextField();
        nameField.setBounds(Consts.WIDTH / 2 - (Consts.WIDTH * 3) / 10, Consts.HEIGHT / 3 + 30, (Consts.WIDTH * 3) / 5, (Consts.HEIGHT / 10));
        nameField.setFont(new Font("Press Start 2P", Font.PLAIN, 30));

        // Tạo nhãn và trường nhập mã phòng chơi
        JLabel roomLabel = new JLabel("Join Game Room:");
        roomLabel.setBounds(Consts.WIDTH / 2 - 220, Consts.HEIGHT / 3 + 120, 800, 50);
        roomLabel.setForeground(Color.WHITE);
        roomLabel.setFont(new Font("Press Start 2P", Font.BOLD, 30));

        JTextField roomField = new JTextField();
        roomField.setBounds(Consts.WIDTH / 2 - (Consts.WIDTH * 3) / 10, Consts.HEIGHT / 3 + 180, (Consts.WIDTH * 3) / 5, (Consts.HEIGHT / 10));
        roomField.setFont(new Font("Press Start 2P", Font.PLAIN, 30));

        // Nút Submit
        ImageIcon playIcon = new ImageIcon(getClass().getResource("/assets/Startbutton.png"));
        Image imgButton = playIcon.getImage().getScaledInstance(100, 30, Image.SCALE_SMOOTH);
        JButton submitButton = new JButton(new ImageIcon(imgButton));
        submitButton.setBounds(Consts.WIDTH / 2 - 50, Consts.HEIGHT / 2 + 200, 100, 30);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WaitingScreen();
                dispose();
            }
        });

        // Thêm các thành phần vào JFrame
        add(nameLabel);
        add(nameField);
        add(roomLabel);
        add(roomField);
        add(submitButton);
        add(background);

        // Hiển thị JFrame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainJframe::new);
    }
}
