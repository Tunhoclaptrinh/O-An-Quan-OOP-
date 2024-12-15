package Network;

import GameControler.Test_LOGIC;
import GameGUI.Arrow;
import GameGUI.Chooser;
import GameGUI.Consts;
import GameGUI.MusicPlayer;
import Model.OCo.OCo;
import Model.OCo.ODan;
import Model.OCo.OQuan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static GameGUI.Consts.BACKGROUND_path;
import static GameGUI.Consts.t;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("O An Quan - Game");
        setSize(800, 600); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JLabel background = new JLabel();

        // Tải và scale hình nền
        ImageIcon icon = new ImageIcon("C:/Users/Phong/Downloads/O-An-Quan-OOP--main (13)/O-An-Quan-OOP--main/src/Assets/1x/Asset 3.png");
        Image img = icon.getImage().getScaledInstance(Consts.WIDTH, Consts.HEIGHT, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);

        // Thiết lập lớp phủ và nội dung cho JFrame
        setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        layeredPane.setLayout(null);

        // Thêm hình nền và label vào cửa sổ
        layeredPane.add(background);


        // Tạo cửa sổ điều khiển
        Control cw = new Control();
        cw.setOpaque(false); // Đảm bảo cửa sổ điều khiển không che khuất nền
        cw.setBounds(0, 0, Consts.WIDTH, Consts.HEIGHT);
        layeredPane.add(cw, JLayeredPane.PALETTE_LAYER);

        // Hiển thị cửa sổ game
        this.setVisible(true);
        Test_LOGIC.P();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow();
            gameWindow.setVisible(true); // Hiển thị cửa sổ game
        });
    }

}

class Control extends JPanel implements ActionListener, KeyListener {
    private Timer timer = new Timer(10, this);

    public static Image BACKGROUND = t.getImage(Consts.BACKGROUND_path);

    // Các đối tượng Chọn và Mũi tên
    public Chooser chooser = new Chooser();
    public Arrow arrowR = new Arrow("p");
    public Arrow arrowL = new Arrow("t");

    // Cài đặt font cho giao diện game
    private Font gameFont = new Font("Press Start 2P", Font.PLAIN, Consts.FONT_SIZE);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Scale hình nền sao cho vừa với cửa sổ
        int newWidth = Consts.WIDTH;
        int newHeight = Consts.HEIGHT;

        // Vẽ hình nền
        g.drawImage(BACKGROUND, 0, 0, newWidth, newHeight, this);

        // Bật chế độ khử răng cưa để vẽ mượt hơn
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Thiết lập độ dày đường viền cho bàn cờ
        g2d.setStroke(new BasicStroke(OCo.THICKNESS));

        // Đặt màu cho các thành phần
        g.setColor(Color.WHITE);

        // Đặt font và vẽ một số văn bản (ví dụ)
        g.setFont(gameFont);
        g.setColor(Color.GREEN);
        Font font = g.getFont();

        FontMetrics metrics = g.getFontMetrics(font);

        String text0 = /*Server.player2.getName() +*/ " :Player2 ";
        String textScore2 = /*Server.player2.sumQuanAndDans() +*/  "  : Score " ;
        int textWidth0 = metrics.stringWidth(text0);
        int textWidthScore = metrics.stringWidth(textScore2);

        // Hiển thị tên người chơi
        g.drawString(" Player1: " /*+ Server.player1.getName()*/, 3, 40);
        g.drawString( /*+ Server.player2.getName()*/ " :Player2", Consts.WIDTH - textWidth0, 40);

        // Hiển thị điểm người chơi
        g.drawString(" Score: " /* + Server.player1.sumQuanAndDans()*/, 3, 90);
        g.drawString(/*Server.player2.sumQuanAndDans() +*/ "  : Score" , Consts.WIDTH - textWidthScore, 90);



        //Hiển thị lượt của người chơi
        g2d.setColor(Color.YELLOW);
        // Chuỗi cần đo kích thước
        String text1 = "Current Turn Player: " /*+ Server.player1.getName()*/;
        String text2 = "Current Turn Player: " /*+ Server.player2.getName()*/;


        // Đo chiều rộng và chiều cao của chuỗi
        int textWidth1 = metrics.stringWidth(text1);
        int textWidth2 = metrics.stringWidth(text2);
        //

        int textHeight = metrics.getHeight();

//        if (Server.currentPlayer == Server.player1.getPlayer_id()) {
//            g.drawString("Current Turn Player: " /*+ Server.player1.getName()*/ , Consts.WIDTH/2 - textWidth1/2 , Consts.HEIGHT/4 - textHeight /*- ODan.HEIGHT*/);
//        }
//        else if (Server.currentPlayer == Server.player2.getPlayer_id()) {
//            g.drawString("Current Turn Player: " /*+ Server.player2.getName()*/ , Consts.WIDTH/2 - textWidth2/2, Consts.HEIGHT/4 - textHeight /*- ODan.HEIGHT*/);
//        }

        String currentStones = "Current Stones: 6" /*+ Server.currentStones*/;

        int textWidth3 = metrics.stringWidth(currentStones);


        g.drawString("Current Stones: " /*+ Server.currentStones*/ + "6" , Consts.WIDTH/2 - textWidth3/2 , Consts.HEIGHT/4 + textHeight);

        // Vẽ Ô Quan
        g.setColor(OQuan.oQuanColor);

        // Vẽ viền nửa đường tròn
        g2d.setColor(Color.WHITE);
        g2d.drawArc(OQuan.x /*- ODan.WITH*/ , OQuan.y, 2* ODan.WIDTH, 2*ODan.HEIGHT, 90, 180);
        g2d.drawArc(OQuan.x + 5*ODan.WIDTH  , OQuan.y, 2*ODan.WIDTH, 2*ODan.HEIGHT, -90, 180);


        //Vẽ Ô Dân
        g.setColor(ODan.oDanColor);
        for (int i = 1; i < 6; i++){
            g.drawRect(OCo.x + i*ODan.WIDTH, OCo.y, ODan.WIDTH, ODan.HEIGHT);
            g.drawRect(OCo.x + i*ODan.WIDTH,OCo.y + ODan.HEIGHT, ODan.WIDTH, ODan.HEIGHT);
        }


        g2d.setColor(Color.GREEN);
        //Vẽ điểm của Ô Dân
        for (int i = 0; i < 5; i++){
            g.drawString(  "" + ODan.sumDans(Test_LOGIC.oDans.get(i).getDans()), OCo.x + OQuan.WIDTH/2 + i*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2 , OCo.y + 2*ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
        }

        for (int i = 10; i > 5; i-- ){
            g.drawString(  "" + ODan.sumDans(Test_LOGIC.oDans.get(5 + 10-i).getDans()), OCo.x + OQuan.WIDTH/2 + (i-6)*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2, OCo.y + ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
        }

        //Vẽ điểm ô Quan
        g.drawString(  "" + OQuan.sumQuanAndDans(Test_LOGIC.oQuans.get(1).getQuan(),Test_LOGIC.oQuans.get(1).getDans()), OCo.x + OQuan.WIDTH/2 - ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y + OQuan.WIDTH/4 + (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan trái
        g.drawString(  "" + OQuan.sumQuanAndDans(Test_LOGIC.oQuans.get(0).getQuan(),Test_LOGIC.oQuans.get(0).getDans()), OCo.x + OQuan.WIDTH/2  + 5*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y +  OQuan.WIDTH/4 +  (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan phải

        // Set độ dày cho Chooser
        g2d.setStroke(new BasicStroke(Chooser.THICKNESS));
        // Set màu cho Chooser
        g.setColor(Chooser.chooserColor);
        // Vẽ Chooser
        g.drawRect(Chooser.x, Chooser.y, Chooser.WIDTH, Chooser.HEIGHT);
//        if (Chooser.Choosen) {
//            // Thiết lập màu cho mũi tên
//            g2d.setColor(Arrow.Default_arrowColor);
//            g2d.setStroke(new BasicStroke(Chooser.THICKNESS) );
//
//            // Vẽ đầu mũi tên bên trái
//            g2d.setColor(arrowL.arrowColor);
//            g2d.fillPolygon(arrowL.xPoints, arrowL.yPoints, 3);
//
//            // Vẽ viền đen cho đầu mũi tên bên trái
//            g2d.setColor(Color.BLACK);
//            g2d.drawPolygon(arrowL.xPoints, arrowL.yPoints, 3);
//
//            // Vẽ đầu mũi tên bên phải
//            g2d.setColor(arrowR.arrowColor);
//            g2d.fillPolygon(arrowR.xPoints, arrowR.yPoints, 3);
//
//            // Vẽ viền đen cho đầu mũi tên bên phải
//            g2d.setColor(Color.BLACK);
//            g2d.drawPolygon(arrowR.xPoints, arrowR.yPoints, 3);
//
//        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Xử lý sự kiện từ Timer (chưa triển khai)
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        // Xử lý sự kiện khi phím được gõ (chưa triển khai)
    }

    @Override public void keyPressed(KeyEvent e) {
        MusicPlayer musicPressPlayer = new MusicPlayer();

        musicPressPlayer.playPressMusic(Consts.pressMusicPath);
        if (!Arrow.isChoosingDirection) {
            //Mũi tên LEN, XUONG
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                Chooser.count_y += 1;

                if (Chooser.count_y > 1) {
                    Chooser.count_y = 0;
                }

                Chooser.y = (Consts.HEIGHT / 2 + 3 * OCo.THICKNESS / 2) - Chooser.count_y * ODan.HEIGHT;
                arrowR.updateArrowPositions();
                arrowL.updateArrowPositions();
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                Chooser.count_y -= 1;
                if (Chooser.INDEX == 1 || Chooser.INDEX == 9 ) {

                }
                if (Chooser.count_y < 0) {
                    Chooser.count_y = 1;
                }


                Chooser.y = (Consts.HEIGHT / 2 + 3 * OCo.THICKNESS / 2) - Chooser.count_y * ODan.HEIGHT;
                arrowR.updateArrowPositions();
                arrowL.updateArrowPositions();
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_F) {
                Chooser.count_x += 1;
//                if (Cho)
                Chooser.INDEX +=1;
                if (Chooser.count_x > 2) {
                    Chooser.count_x = -2;
                }
                if (Chooser.INDEX == 5){
                    Chooser.INDEX = 0;
                }


                Chooser.x = ((Consts.WIDTH - Chooser.WIDTH) / 2) + Chooser.count_x * ODan.WIDTH;
                arrowR.updateArrowPositions();
                arrowL.updateArrowPositions();
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                Chooser.count_x -= 1;
                Chooser.INDEX -=1;
                if (Chooser.count_x < -2) {
                    Chooser.count_x = 2;
                }
                if (Chooser.INDEX == -1){
                    Chooser.INDEX = 4;
                }

                Chooser.x = ((Consts.WIDTH - Chooser.WIDTH) / 2) + Chooser.count_x * ODan.WIDTH;
                arrowR.updateArrowPositions();
                arrowL.updateArrowPositions();
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (Chooser.Choosen){
                    Chooser.Choosen = false;
                    Arrow.isChoosingDirection = false;
                }
                else if (!Chooser.Choosen){
                    Chooser.Choosen = true;
                    Arrow.isChoosingDirection = true;
//                    Test_LOGIC.isWaitingForInput = false; // Gửi tín hiệu tới logic

                }
            }


        } else {
            // Đang chọn chiều
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                if (Arrow.selectedDirection.isEmpty() || Arrow.selectedDirection == "p") {
                    Arrow.selectedDirection = "t";
                    arrowL.setArrowColor(); // Đổi màu mũi tên phải để hiển thị đã chọn
                    arrowR.arrowColor = Arrow.Default_arrowColor;
                }

                else if (!Arrow.selectedDirection.isEmpty()) {
                    Arrow.isChoosingDirection = false;
                    Arrow.selectedDirection = "";
                    Chooser.Choosen = false;
                    System.out.println("Chiều chưa được chọn");
                    arrowL.setArrowColor();
                    arrowR.setArrowColor();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_F ) {
                if (Arrow.selectedDirection.isEmpty() || Arrow.selectedDirection == "t") {
                    Arrow.selectedDirection = "p";
                    arrowR.setArrowColor(); // Đổi màu mũi tên phải để hiển thị đã chọn
                    arrowL.arrowColor = Arrow.Default_arrowColor;
                }

                else if (!Arrow.selectedDirection.isEmpty()) {
                    Arrow.isChoosingDirection = false;
                    Arrow.selectedDirection = "";
                    Chooser.Choosen = false;
                    System.out.println("Chiều chưa được chọn");

                    arrowL.setArrowColor();
                    arrowR.setArrowColor();
                }
            }




            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE ) {
                // Kết thúc chọn chiều, thực hiện hành động tiếp theo
                Arrow.isChoosingDirection = false;
                if (!Arrow.selectedDirection.isEmpty()) {
                    System.out.println("Chiều đã chọn: " + Arrow.selectedDirection);
                }
                else {
                    System.out.println("Ô chưa được chọn!");
                    Test_LOGIC.currentPlayer ++;
                }

//                Test_LOGIC.isWaitingForInput = false; // Gửi tín hiệu tới logic
                // Reset trạng thái để chuẩn bị cho lượt tiếp theo
//                Arrow.selectedDirection = "";
                Chooser.Choosen = false;
                arrowL.setArrowColor();
                arrowR.setArrowColor();


            }
        }

        Chooser.setChoosen(Chooser.Choosen); // reset chooserColor

    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        // Xử lý sự kiện khi phím được thả ra (chưa triển khai)
    }
}
