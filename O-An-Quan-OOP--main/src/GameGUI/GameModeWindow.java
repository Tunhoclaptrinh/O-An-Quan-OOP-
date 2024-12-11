package GameGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameModeWindow extends JFrame {

    public GameModeWindow() {
        // Thiết lập
        this.setTitle("Chọn Chế Độ Chơi");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mở lại StartMenu
                new StartMenu();
            }
        });

        // Tạo giao diện
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Khoảng cách giữa các nút


        JButton twoPlayerButton = new JButton("Chế Độ 2 Người");
        twoPlayerButton.setFont(new Font("Arial", Font.BOLD, 30));
        twoPlayerButton.setBackground(Color.CYAN);
        twoPlayerButton.setForeground(Color.BLACK);
        twoPlayerButton.setBorderPainted(false);
        twoPlayerButton.setFocusPainted(false);
        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainJframe();
                dispose(); // Đóng cửa sổ chế độ chơi
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(twoPlayerButton, gbc);

        // Chơi với máy test
        JButton onePlayerButton = new JButton("Chế Độ 1 Người");
        onePlayerButton.setFont(new Font("Arial", Font.BOLD, 30));
        onePlayerButton.setBackground(Color.CYAN);
        onePlayerButton.setForeground(Color.BLACK);
        onePlayerButton.setBorderPainted(false);
        onePlayerButton.setFocusPainted(false);
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GameModeWindow.this,
                        "Test", "1 người", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(onePlayerButton, gbc);

        this.add(panel);
        this.setVisible(true);

    }
}
