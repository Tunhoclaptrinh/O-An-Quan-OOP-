package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {
    public StartMenu() {
        this.setTitle("O An Quan - Menu");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Tạo giao diện nút
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //Khoảng cách của các nút
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
                JOptionPane.showMessageDialog(StartMenu.this, "test","test", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(testButton, gbc);

        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new StartMenu();
    }
}
