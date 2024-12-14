package GameGUI;

import javax.swing.*;
import java.awt.*;

public class MatchResultWindow extends JFrame {
    public MatchResultWindow(String player1Name, int player1Score, String player2Name, int player2Score) {
        setTitle("GAME OVER");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(70, 130, 180)); // Steel Blue background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Press Start 2P", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hiện điểm panel
        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new GridLayout(2, 2, 10, 10));
        scoresPanel.setBackground(new Color(70, 130, 180));

        JLabel player1Label = new JLabel(player1Name + ":", SwingConstants.RIGHT);
        player1Label.setForeground(Color.WHITE);
        player1Label.setFont(new Font("Press Start 2P", Font.BOLD, 20));

        JLabel player1ScoreLabel = new JLabel(String.valueOf(player1Score), SwingConstants.LEFT);
        player1ScoreLabel.setForeground(Color.WHITE);
        player1ScoreLabel.setFont(new Font("Press Start 2P", Font.BOLD, 20));

        JLabel player2Label = new JLabel(player2Name + ":", SwingConstants.RIGHT);
        player2Label.setForeground(Color.WHITE);
        player2Label.setFont(new Font("Press Start 2P", Font.BOLD, 20));

        JLabel player2ScoreLabel = new JLabel(String.valueOf(player2Score), SwingConstants.LEFT);
        player2ScoreLabel.setForeground(Color.WHITE);
        player2ScoreLabel.setFont(new Font("Press Start 2P", Font.BOLD, 20));

        scoresPanel.add(player1Label);
        scoresPanel.add(player1ScoreLabel);
        scoresPanel.add(player2Label);
        scoresPanel.add(player2ScoreLabel);

        String winnerText = determineWinner(player1Name, player1Score, player2Name, player2Score);
        JLabel winnerLabel = new JLabel(winnerText, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Press Start 2P", Font.BOLD, 24));
        winnerLabel.setForeground(Color.YELLOW);
        winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some vertical space
        mainPanel.add(scoresPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add some vertical space
        mainPanel.add(winnerLabel);

        add(mainPanel);
    }

    private String determineWinner(String player1Name, int player1Score, String player2Name, int player2Score) {
        if (player1Score > player2Score) {
            return player1Name + " Wins!";
        } else if (player2Score > player1Score) {
            return player2Name + " Wins!";
        } else {
            return "Tie!";
        }
    }

    // Lấy ra từ Test_LOGIC
    public static void showGameOverWindow(String player1Name, int player1Score, String player2Name, int player2Score) {
        SwingUtilities.invokeLater(() -> {
            MatchResultWindow gameOverWindow = new MatchResultWindow(player1Name, player1Score, player2Name, player2Score);
            gameOverWindow.setVisible(true);
        });
    }
}