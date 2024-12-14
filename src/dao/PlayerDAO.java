package dao;

import GameGUI.Consts;
import GameGUI.MainJframe;
import GameGUI.StartMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class PlayerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/game_scores";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public void saveScoreToDatabase(String playerName, int score) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO player_scores (player_name, score) values (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, playerName);
                preparedStatement.setInt(2, score);
                preparedStatement.executeUpdate();
                System.out.println("Saved score for player: " + playerName);
            }
        } catch (SQLException e) {
            System.err.println("Error while saving score for player: " + playerName);
            e.printStackTrace();
        }
    }

    public void displayAllScores() {
        JFrame frame = new JFrame("SCORES BOARD");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(Consts.WIDTH, Consts.HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Tiêu đề
        JLabel titleLabel = new JLabel("SCORES BOARD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Press Start 2P", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.PINK);
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Tạo model bảng
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Player's Name", "High Scores", "Game Date"}, 0);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM player_scores ORDER BY score DESC";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String playerName = resultSet.getString("player_name");
                    int score = resultSet.getInt("score");
                    Date gameDate = resultSet.getDate("game_date");
                    tableModel.addRow(new Object[]{playerName, score, gameDate});
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching scores.");
            e.printStackTrace();
        }

        // Tạo bảng với model
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.PINK);
        table.getTableHeader().setForeground(Color.YELLOW);

        // Cuộn bảng
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Nút Close
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Press Start 2P", Font.BOLD, 16));
        closeButton.setBackground(Color.GREEN);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(e -> frame.dispose());
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartMenu();
                dispose();
            }

            //Thêm dispose cho nút Close
            private void dispose() {
            }
        });
        JPanel buttonPanel = new JPanel();


        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
