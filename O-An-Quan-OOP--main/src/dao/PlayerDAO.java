package dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PlayerDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/game_scores";
    private static final String USER = "root";
    private static final String PASSWORD = "2005";

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
        JFrame frame = new JFrame("Bảng điểm người chơi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Tên người chơi", "Điểm"}, 0);

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM player_scores ORDER BY score DESC";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String playerName = resultSet.getString("player_name");
                    int score = resultSet.getInt("score");
                    tableModel.addRow(new Object[]{playerName, score});
                }
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching scores.");
            e.printStackTrace();
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
