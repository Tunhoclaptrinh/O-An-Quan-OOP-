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
        this.setTitle("O An Quan - Menu");
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

        // Tạo giao diện nút
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Khoảng cách của các nút
        gbc.insets = new Insets(10, 0, 10, 0);

        // Nút "CHƠI NGAY"
        JButton playButton = new JButton("CHƠI NGAY");
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.setBackground(Color.GREEN);
        playButton.setForeground(Color.BLACK);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestGUI();
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(playButton, gbc);

        // Nút "TEST"
        JButton testButton = new JButton("TEST");
        testButton.setFont(new Font("Arial", Font.BOLD, 20));
        testButton.setBackground(Color.GREEN);
        testButton.setForeground(Color.WHITE);
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(StartMenu.this, "test", "test", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(testButton, gbc);

        // Thêm panel vào frame
        this.add(panel);
        this.setVisible(true);
    }

    // Override phương thức paintComponent để vẽ hình nền
    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Gọi phương thức paint của JFrame để vẽ lại các thành phần khác
        if (backgroundImage != null) {
            // Kích thước mới (scale theo ý muốn)
            int newWidth = Consts.WIDTH;  // Chiều rộng mong muốn => lấy theo kích cỡ màn hình
            int newHeight = Consts.HEIGHT; // Chiều cao mong muốn => Lấy theo kích  cỡ màn hình

            // Scale hình ảnh
            g.drawImage(backgroundImage, 0, 0, newWidth, newHeight, this);

//        // Tính toán vị trí để căn giữa
//        int x = (panelWidth - imageWidth) / 2;
//        int y = (panelHeight - imageHeight) / 2;


            // Vẽ hình ảnh
            g.drawImage(backgroundImage, 0, 0, newWidth,newHeight,this);
        }
    }

    public static void main(String[] args) {
        new StartMenu();
    }
}
