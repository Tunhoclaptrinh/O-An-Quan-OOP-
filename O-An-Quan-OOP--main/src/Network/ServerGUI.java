package Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



import GameGUI.Arrow;
import GameGUI.Chooser;
import GameGUI.Consts;
import GameGUI.StartMenu;
import Model.OCo.*;
import javax.swing.*;
import Model.OCo.OQuan;
import Model.Player.Player;

import static GameControler.Test_LOGIC.isWaitingForInput;
import static GameGUI.Consts.t;


public class ServerGUI extends JFrame {
    private ControlWindow cw = new ControlWindow();
    public ServerGUI() {
        this.pack();
        this.setTitle("O An Quan");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    public static void WaitingForInput (){
        while (isWaitingForInput) {
            try {
                Thread.sleep(100); // Tạm dừng để tránh tiêu tốn tài nguyên
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
        // Reset trạng thái sau khi nhận tín hiệu
        isWaitingForInput = true;
    }


    public static void main(String[] args) throws InterruptedException {
        StartMenu.main(null);

        Server.playGame();    }
}

class ControlWindow extends JPanel implements ActionListener, KeyListener {
    private Timer timer = new Timer(10, this);

    public static Image BACKGROUND = t.getImage(Consts.BACKGROUND_path);


    // Ô Chọn
    public Chooser chooser = new Chooser();

    // Mũi tên chọn chiều
    public Arrow arrowR = new Arrow( "p");
    public Arrow arrowL = new Arrow("t");

    // Set font và font-size
    private Font gameFont = new Font("Press Start 2P" , Font.PLAIN, Consts.FONT_SIZE);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Kích thước mới (scale theo ý muốn)
        int newWidth = Consts.WIDTH;  // Chiều rộng mong muốn => lấy theo kích cỡ màn hình
        int newHeight = Consts.HEIGHT; // Chiều cao mong muốn => Lấy theo kích  cỡ màn hình

        // Scale hình ảnh
        g.drawImage(BACKGROUND, 0, 0, newWidth, newHeight, this);

        // Vẽ hình ảnh
        g.drawImage(BACKGROUND, 0, 0, newWidth,newHeight,this);

        // Bật chế độ khử răng cưa để vẽ mượt hơn
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the stroke (line thickness) to 5 , set độ dày cho bàn cờ
        g2d.setStroke(new BasicStroke(OCo.THICKNESS));

        g.setColor(Color.WHITE); //set màu cho mọi thứ

        g.setColor(Color.GREEN);
        g.setFont(gameFont);

        // Hiển thị tên người chơi
        g.drawString("Player1: " + Server.player1, 5, 40);
        g.drawString( Server.player2 + " :Player2", Consts.WIDTH - 250 - 130, 40);
//
//        // Hiển thị điểm người chơi
        g.drawString("Score: " + Server.player1.sumQuanAndDans(), 5, 90);
        g.drawString(Server.player2.sumQuanAndDans() + "  : Score" , Consts.WIDTH - 250 - 70, 90);

        //Hiển thị lượt của người chơi
        g2d.setColor(Color.YELLOW);
        // Chuỗi cần đo kích thước
        String text1 = "Luot choi Player1: " + Server.player1;
        String text2 = "Luot choi Player2: " + Server.player2;
        // Lấy font hiện tại
        Font font = g.getFont();
        // Lấy FontMetrics từ Graphics
        FontMetrics metrics = g.getFontMetrics(font);

        // Đo chiều rộng và chiều cao của chuỗi
        int textWidth1 = metrics.stringWidth(text1);
        int textWidth2 = metrics.stringWidth(text2);
        //

        int textHeight = metrics.getHeight();

        if (Server.currentPlayer == Server.player1.getPlayer_id()) {
            g.drawString("Luot choi Player1: " + Server.player1 , Consts.WIDTH/2 - textWidth1/2 , Consts.HEIGHT/4 - textHeight /*- ODan.HEIGHT*/);
        }
        else if (Server.currentPlayer == Server.player2.getPlayer_id()) {
            g.drawString("Luot choi Player2: " + Server.player2, Consts.WIDTH/2 - textWidth2/2, Consts.HEIGHT/4 - textHeight /*- ODan.HEIGHT*/);
        }

        // Vẽ Ô Quan
        g.setColor(OQuan.oQuanColor);

        // Vẽ viền nửa đường tròn
        g2d.setColor(Color.WHITE);
        g2d.drawArc(OQuan.x /*- ODan.WITH*/ , OQuan.y, 2*ODan.WIDTH, 2*ODan.HEIGHT, 90, 180);
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
            g.drawString(  "" + ODan.sumDans(Server.oDans.get(i).getDans()), OCo.x + OQuan.WIDTH/2 + i*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2 , OCo.y + 2*ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
        }

        for (int i = 10; i > 5; i-- ){
            g.drawString(  "" + ODan.sumDans(Server.oDans.get(5 + 10-i).getDans()), OCo.x + OQuan.WIDTH/2 + (i-5)*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2, OCo.y + ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
        }

        //Vẽ điểm ô Quan
        g.drawString(  "" + OQuan.sumQuanAndDans(Server.oQuans.get(1).getQuan(),Server.oQuans.get(1).getDans()), OCo.x + OQuan.WIDTH/2 - ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y + OQuan.WIDTH/4 + (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan trái
        g.drawString(  "" + OQuan.sumQuanAndDans(Server.oQuans.get(0).getQuan(),Server.oQuans.get(0).getDans()), OCo.x + OQuan.WIDTH/2  + 5*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y +  OQuan.WIDTH/4 +  (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan phải

        // Set độ dày cho Chooser
        g2d.setStroke(new BasicStroke(Chooser.THICKNESS));
        // Set màu cho Chooser
        g.setColor(Chooser.chooserColor);
        // Vẽ Chooser
        g.drawRect(Chooser.x, Chooser.y, Chooser.WIDTH, Chooser.HEIGHT);

        //
        if (Chooser.Choosen) {
            // Thiết lập màu cho mũi tên
            g2d.setColor(Arrow.Default_arrowColor);
            g2d.setStroke(new BasicStroke(Chooser.THICKNESS) );

            // Vẽ đầu mũi tên bên trái
            g2d.setColor(arrowL.arrowColor);
            g2d.fillPolygon(arrowL.xPoints, arrowL.yPoints, 3);

            // Vẽ viền đen cho đầu mũi tên bên trái
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrowL.xPoints, arrowL.yPoints, 3);

            // Vẽ đầu mũi tên bên phải
            g2d.setColor(arrowR.arrowColor);
            g2d.fillPolygon(arrowR.xPoints, arrowR.yPoints, 3);

            // Vẽ viền đen cho đầu mũi tên bên phải
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrowR.xPoints, arrowR.yPoints, 3);

        }
    }


    // Vẽ lại màn hình
    @Override public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override public void keyPressed(KeyEvent e) {

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
                    isWaitingForInput = false; // Gửi tín hiệu tới logic

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
                    Server.currentPlayer ++;
                }

                isWaitingForInput = false; // Gửi tín hiệu tới logic
                // Reset trạng thái để chuẩn bị cho lượt tiếp theo
//                Arrow.selectedDirection = "";
                Chooser.Choosen = false;
                arrowL.setArrowColor();
                arrowR.setArrowColor();


            }
        }

        Chooser.setChoosen(Chooser.Choosen); // reset chooserColor

    }

    @Override public void keyReleased(KeyEvent e) {
        //Chờ thả mới hoạt động
    }

    @Override public void keyTyped(KeyEvent e) {
        //Nhấn vào thả ra mới nhận
    }



    public ControlWindow(){
        timer.start();
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);

    }
}