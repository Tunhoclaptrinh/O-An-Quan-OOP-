package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameRule extends JFrame {
    public GameRule() {
        // Set up the main frame with a more modern look
        setTitle("Luật Chơi Game Ô Ăn Quan");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 255)); // Light blue background

        // Add a window listener for confirmation on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        GameRule.this,
                        "Bạn có chắc muốn thoát không?",
                        "Xác Nhận Thoát",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Create a JTextArea to display the instructions with improved styling
        JTextArea textArea = new JTextArea();
        textArea.setText("Di chuyển quân: từng người chơi khi đến lượt của mình sẽ di chuyển dân theo phương án để có thể ăn được càng nhiều dân và quan hơn đối phương càng tốt. Người thực hiện lượt đi đầu tiên thường được xác định bằng cách oẳn tù tì hay thỏa thuận. Khi đến lượt, người chơi sẽ dùng tất cả số quân trong một ô có quân bất kỳ do người đó chọn trong số 5 ô vuông thuộc quyền kiểm soát của mình để lần lượt rải vào các ô, mỗi ô 1 quân, bắt đầu từ ô gần nhất và có thể rải ngược chiều xuôi chiều kim đồng hồ tùy ý. Khi rải hết quân cuối cùng, tùy tình huống mà người chơi sẽ phải xử lý tiếp như sau:\n\n" +
                "Nếu liền sau đó là một ô vuông có chứa quân thì tiếp tục dùng tất cả số quân đó để rải tiếp,theo chiều đã chọn.\n" +
                "Nếu liền sau đó là một ô trống (không phân biệt ô quan hay ô dân) rồi đến một ô có chứa quân thì người chơi sẽ bị mất lượt số quân trong ô đó. Số quân bị ăn sẽ được loại ra khỏi bàn chơi để người chơi tính điểm khi kết thúc. Nếu liền sau ô có quân đã bị ăn lại là một ô trống rồi đến một ô có quân nữa thì người chơi có quyền ăn tiếp cả quân ở ô này... Do đó trong cuộc chơi có thể có phương án rải quân làm cho người chơi ăn hết toàn bộ số quân trên bàn chơi chỉ trong một lượt đi của mình. Một ô có nhiều dân thường được trẻ em gọi là ô nhà giàu, rất nhiều dân thì gọi là giàu sụ. Người chơi có thể bằng kinh nghiệm hoặc tính toán phương án nhằm nuôi ô nhà giàu rồi mới ăn để được nhiều điểm.\n" +
                "Nếu liền sau đó là ô quan có chứa quân hoặc 2 ô trống trở lên hoặc sau khi vừa ăn thì người chơi bị mất lượt và quyền đi tiếp thuộc về đối phương.\n" +
                "Trường hợp đến lượt đi nhưng cả năm ô vuông thuộc quyền kiểm soát của người chơi đều không có dân thì người đó sẽ phải dùng 5 dân đã ăn được của mình để đặt vào mỗi ô 1 dân để có thể thực hiện việc di chuyển quân. Nếu người chơi không đủ 5 dân thì phải vay của đối phương và trả lại khi tính điểm. Cuộc chơi sẽ kết thúc khi toàn bộ dân và quan ở hai ô quan đã bị ăn hết. Trường hợp hai ô quan đã bị ăn hết nhưng vẫn còn dân thì quân trong những hình vuông phía bên nào coi như thuộc về người chơi bên ấy; tình huống này được gọi là hết quan, tàn dân, thu quân, kéo về hay hết quan, tàn dân, thu quân, bán ruộng. Ô quan có ít dân (có số dân nhỏ hơn 5 phổ biến được coi là ít) gọi là quan non và để cuộc chơi không bị kết thúc sớm cho tăng phần thú vị, luật chơi có thể quy định không được ăn quan non, nếu rơi vào tình huống đó sẽ bị mất lượt.");

        // Improved text area styling
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(255, 255, 240)); // Light yellow background for text area
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createLineBorder(new Color(200, 200, 220), 2, true)
        ));

        // Add the text area to a scroll pane with improved scrollbar
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            private Color thumbDarkShadow;
            private Color thumbLightShadow;

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(180, 180, 220);
                this.thumbDarkShadow = new Color(100, 100, 150);
                this.thumbLightShadow = new Color(220, 220, 255);
                this.trackColor = new Color(230, 230, 250);
            }
        });
        add(scrollPane, BorderLayout.CENTER);

        // Create the Close button with original styling
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Press Start 2P", Font.BOLD, 16));
        closeButton.setBackground(Color.GREEN);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StartMenu();
            }
        });

        // Add the button to a panel and position it at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 255));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameRule::new);
    }
}