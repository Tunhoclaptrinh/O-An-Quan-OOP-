package dao;

import java.sql.*;

public class GameStateDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/game_state";
    private static final String USER = "root";
    private static final String PASSWORD = "2005";

    public void saveGameState(int player1Score, int player2Score, int currentPlayer, String gameBoardJson) {
        String sql = "INSERT INTO game_state (player1_score, player2_score, current_player, game_board) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, player1Score);
            pstmt.setInt(2, player2Score);
            pstmt.setInt(3, currentPlayer);
            pstmt.setString(4, gameBoardJson);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public GameState loadGameState() {
//        String sql = "SELECT * FROM game_state ORDER BY last_updated DESC LIMIT 1";
//
//        try (Connection conn = DriverManager.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            if (rs.next()) {
//                int player1Score = rs.getInt("player1_score");
//                int player2Score = rs.getInt("player2_score");
//                int currentPlayer = rs.getInt("current_player");
//                String gameBoardJson = rs.getString("game_board");
//
//                return new GameState(player1Score, player2Score, currentPlayer, gameBoardJson);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
