//package GameGUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import OCo.OQuan;
//import Player.Player;
//
//public class main2 extends JFrame {
//    private GamePanel gamePanel;
//
//    public main2() {
//        gamePanel = new GamePanel();
//        this.add(gamePanel);
//        this.setTitle("O An Quan");
//        this.setSize(Consts.WIDTH, Consts.HEIGHT);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Main());
//    }
//}
//
//class GamePanel extends JPanel implements MouseListener {
//    private Timer gameTimer;
//    private Player player1;
//    private Player player2;
//    private int[] board;
//    private int currentPlayer;
//    private int selectedHole;
//    private boolean awaitingDirection;
//    private Rectangle[] holeRects;
//    private Font gameFont;
//
//    public GamePanel() {
//        initializeGame();
//        setupUI();
//        setupTimer();
//    }
//
//    private void initializeGame() {
//        player1 = new Player("Tuan");
//        player2 = new Player("Tun");
//        board = new int[12];
//        currentPlayer = 0;
//        selectedHole = -1;
//        awaitingDirection = false;
//        holeRects = new Rectangle[12];
//        gameFont = new Font("Arial", Font.BOLD, 20);
//
//        // Initialize board
//        for (int i = 0; i < 12; i++) {
//            board[i] = 5;
//        }
//        OQuan quanP = new OQuan(5);
//        OQuan quanT = new OQuan(11);
//        board[quanP.getIndex()] = quanP.tinhDiem();
//        board[quanT.getIndex()] = quanT.tinhDiem();
//
//        // Initialize hole rectangles for click detection
//        for (int i = 0; i < 5; i++) {
//            holeRects[i] = new Rectangle(Consts.x + i*100, Consts.y + 100, 100, 100);
//            holeRects[i + 6] = new Rectangle(Consts.x + i*100, Consts.y, 100, 100);
//        }
//        // Special holes (Quan)
//        holeRects[5] = new Rectangle(Consts.x - 100, Consts.y, 100, 200);
//        holeRects[11] = new Rectangle(Consts.x + 500, Consts.y, 100, 200);
//    }
//
//    private void setupUI() {
//        setBackground(Color.BLACK);
//        addMouseListener(this);
//        setFocusable(true);
//    }
//
//    private void setupTimer() {
//        gameTimer = new Timer(16, e -> repaint());
//        gameTimer.start();
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setStroke(new BasicStroke(2));
//
//        // Draw board
//        drawBoard(g2d);
//
//        // Draw scores and player info
//        drawInfo(g2d);
//
//        // Draw selection highlight
//        if (selectedHole >= 0) {
//            g2d.setColor(Color.YELLOW);
//            g2d.draw(holeRects[selectedHole]);
//        }
//
//        // Draw awaiting direction message
//        if (awaitingDirection) {
//            g2d.setColor(Color.WHITE);
//            g2d.drawString("Press LEFT or RIGHT arrow for direction",
//                    Consts.WIDTH/2 - 150, Consts.HEIGHT - 50);
//        }
//    }
//
//    private void drawBoard(Graphics2D g) {
//        // Draw Quan holes
//        g.setColor(Color.WHITE);
//        g.draw(new Rectangle(Consts.x - 100, Consts.y, 100, 200));
//        g.draw(new Rectangle(Consts.x + 500, Consts.y, 100, 200));
//
//        // Draw regular holes
//        for (int i = 0; i < 5; i++) {
//            g.draw(new Rectangle(Consts.x + i*100, Consts.y, 100, 100));
//            g.draw(new Rectangle(Consts.x + i*100, Consts.y + 100, 100, 100));
//        }
//
//        // Draw stone counts
//        g.setFont(gameFont);
//        for (int i = 0; i < 5; i++) {
//            // Bottom row (Player 1)
//            g.drawString(String.valueOf(board[i]),
//                    Consts.x + i*100 + 40, Consts.y + 160);
//            // Top row (Player 2)
//            g.drawString(String.valueOf(board[i + 6]),
//                    Consts.x + i*100 + 40, Consts.y + 60);
//        }
//        // Draw Quan stone counts
//        g.drawString(String.valueOf(board[5]), Consts.x - 60, Consts.y + 100);
//        g.drawString(String.valueOf(board[11]), Consts.x + 540, Consts.y + 100);
//    }
//
//    private void drawInfo(Graphics2D g) {
//        g.setColor(Color.GREEN);
//        g.setFont(gameFont);
//        // Player 1 info
//        g.drawString("Player 1: " + player1.getName(), 20, 30);
//        g.drawString("Score: " + player1.getScore(), 20, 60);
//        // Player 2 info
//        g.drawString("Player 2: " + player2.getName(), Consts.WIDTH - 200, 30);
//        g.drawString("Score: " + player2.getScore(), Consts.WIDTH - 200, 60);
//        // Current player indicator
//        String currentPlayerName = currentPlayer == 0 ? player1.getName() : player2.getName();
//        g.drawString("Current Turn: " + currentPlayerName,
//                Consts.WIDTH/2 - 100, 30);
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (awaitingDirection) return;
//
//        Point click = e.getPoint();
//        for (int i = 0; i < 12; i++) {
//            if (holeRects[i].contains(click)) {
//                handleHoleSelection(i);
//                break;
//            }
//        }
//    }
//
//    private void handleHoleSelection(int hole) {
//        // Validate move
//        if (currentPlayer == 0 && (hole >= 6 || hole == 5 || hole == 11)) return;
//        if (currentPlayer == 1 && (hole < 6 || hole == 5 || hole == 11)) return;
//        if (board[hole] == 0) return;
//
//        selectedHole = hole;
//        awaitingDirection = true;
//
//        // Add key listener for direction
//        KeyAdapter directionListener = new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (!awaitingDirection) return;
//
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    makeMove(selectedHole, "t");
//                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    makeMove(selectedHole, "p");
//                }
//            }
//        };
//
//        this.addKeyListener(directionListener);
//    }
//
//    private void makeMove(int hole, String direction) {
//        int stones = board[hole];
//        board[hole] = 0;
//
//        int startIndex = direction.equals("t") ? hole - 1 : hole + 1;
//        distributeStones(stones, direction, startIndex);
//
//        // Update game state
//        awaitingDirection = false;
//        selectedHole = -1;
//        currentPlayer = (currentPlayer + 1) % 2;
//
//        // Check for game end
//        if (board[5] == 0 && board[11] == 0) {
//            endGame();
//        }
//
//        repaint();
//    }
//
//    private void distributeStones(int stones, String direction, int index) {
//        // Implementation similar to console version, but adapted for GUI
//        if (direction.equals("t")) {
//            distributeLeft(stones, index);
//        } else {
//            distributeRight(stones, index);
//        }
//    }
//
//    private void distributeLeft(int stones, int i) {
//        // Similar to console version
//        // ... (distribution logic)
//        // After distribution, update scores and board state
//        repaint();
//    }
//
//    private void distributeRight(int stones, int i) {
//        // Similar to console version
//        // ... (distribution logic)
//        // After distribution, update scores and board state
//        repaint();
//    }
//
//    private void endGame() {
//        String winner = player1.getScore() > player2.getScore() ?
//                player1.getName() : player2.getName();
//        JOptionPane.showMessageDialog(this,
//                "Game Over! " + winner + " wins with " +
//                        Math.max(player1.getScore(), player2.getScore()) + " points!");
//        initializeGame();
//    }
//
//    // Unused mouse listener methods
//    @Override public void mousePressed(MouseEvent e) {}
//    @Override public void mouseReleased(MouseEvent e) {}
//    @Override public void mouseEntered(MouseEvent e) {}
//    @Override public void mouseExited(MouseEvent e) {}
//}
//
