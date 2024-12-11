package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameModeWindow extends JFrame {

    public GameModeWindow() {
        // Thiết lập JFrame
        this.setTitle("Chọn Chế Độ Chơi");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mở lại StartMenu
                SwingUtilities.invokeLater(StartMenu::new);
            }
        });

        // Thêm hình nền
        JLabel background = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/background.png"));
        Image img = icon.getImage().getScaledInstance(Consts.WIDTH, Consts.HEIGHT, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);

        // Tạo JPanel để chứa các nút
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false); // Đảm bảo nền của panel trong suốt
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0); // Khoảng cách giữa các nút

        // Nút "1 Player Mode"
        JButton onePlayerButton = new JButton("1 Player Mode");
        onePlayerButton.setFont(new Font("Press Start 2P", Font.BOLD, 21));
        onePlayerButton.setBackground(Color.GREEN);
        onePlayerButton.setForeground(Color.BLACK);
        onePlayerButton.setBorderPainted(false);
        onePlayerButton.setFocusPainted(false);
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestGUI();
                dispose(); // Đóng cửa sổ chế độ chơi
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(onePlayerButton, gbc);

        // Nút "2 Players Mode"
        JButton twoPlayerButton = new JButton("2 Players Mode");
        twoPlayerButton.setFont(new Font("Press Start 2P", Font.BOLD, 21));
        twoPlayerButton.setBackground(Color.GREEN);
        twoPlayerButton.setForeground(Color.BLACK);
        twoPlayerButton.setBorderPainted(false);
        twoPlayerButton.setFocusPainted(false);
        twoPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TwoPlayersClient();
                dispose(); // Đóng cửa sổ chế độ chơi
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(twoPlayerButton, gbc);

        // Đặt panel và background vào JFrame
        this.setLayout(null);
        panel.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);
        this.add(panel);
        this.add(background);

        // Hiển thị JFrame
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameModeWindow::new);
    }
}