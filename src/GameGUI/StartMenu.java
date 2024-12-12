package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class StartMenu extends JFrame {

    private BufferedImage backgroundImage;

    public StartMenu() {
        this.setTitle("O An Quan Menu");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Tải hình nền
        try {
            backgroundImage = ImageIO.read(new File(Consts.BACKGROUND_path));  // Đảm bảo đường dẫn đến hình ảnh là chính xác
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tạo panel chính với hình nền
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Khoảng cách giữa các thành phần

        // Tiêu đề "O AN QUAN GAME"
        JLabel titleLabel = new JLabel("O AN QUAN GAME");
        titleLabel.setFont(new Font("Press Start 2P", Font.BOLD, 32));
        titleLabel.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(15, 0, 10, 0); // Khoảng cách từ trên xuống tiêu đề
        mainPanel.add(titleLabel, gbc);

        // Nút "START"
        JButton playButton = new JButton("Start");
        playButton.setFont(new Font("Press Start 2P", Font.BOLD, 20));
        playButton.setBackground(Color.GREEN);
        playButton.setForeground(Color.BLACK);
        playButton.setBorderPainted(false);     // Không vẽ viền
        playButton.setFocusPainted(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainJframe();
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Đặt nút ở giữa
        gbc.insets = new Insets(10, 0, 10, 0); // Khoảng cách giữa nút và các thành phần khác
        mainPanel.add(playButton, gbc);

        // Thêm panel chính vào JFrame
        this.setContentPane(mainPanel);

        // Hiển thị JFrame
        this.setVisible(true);
    }

    // Lớp tùy chỉnh JPanel để vẽ hình nền
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, Consts.WIDTH, Consts.HEIGHT, this);
            }
        }
    }

    public static void main(String[] args) {
        new StartMenu();
    }
}