package GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import GameControler.GamePlay;
import GameControler.TestForTwo;
import Player.Player;
import Da.*;
import OCo.*;

import OCo.OQuan;
import Player.Player;

import java.util.Scanner;
import java.util.Arrays;





public class TestGUI extends JFrame {
    private ControlWindow cw = new ControlWindow();

    public TestGUI() {
        this.add(cw);
        this.pack();
        this.setTitle("O An Quan");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestGUI();
    }
}

class ControlWindow extends JPanel implements ActionListener, KeyListener {

    private Timer timer = new Timer(10, this);
    static Test test = new Test();
    private static Player player1 = test.player1;
    private static Player player2 = test.player2;

    private static int currentPlayer = 0;
    public Arrow arrowR = new Arrow( "p");
    public Arrow arrowL = new Arrow("t");

    public Chooser chooser = new Chooser();


    private Font gameFont = new Font("Press Start 2P" , Font.PLAIN, Consts.FONT_SIZE);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Bật chế độ khử răng cưa để vẽ mượt hơn
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //

        // Set the stroke (line thickness) to 5 , set độ dày cho bàn cờ
        g2d.setStroke(new BasicStroke(OCo.THICKNESS));

        g.setColor(Color.WHITE); //set màu cho mọi thứ

        g.setColor(Color.GREEN);
        g.setFont(gameFont);

        // Hiển thị tên người chơi
        g.drawString("Player1: " + player1.getName(), 5, 40);
        g.drawString( player2.getName() + " :Player2", Consts.WIDTH - 250 - 130, 40);
        //Hiển thị điểm người chơi
//        g.drawString("Score: " + player1.getScore(), 5, 90);
//        g.drawString(player2.getScore() + "  : Score" , Consts.WIDTH - 250 - 70, 90);

        // Vẽ Ô Quan
        g.setColor(OQuan.oQuanColor);
        g.drawOval(OQuan.x /*- ODan.WITH*/ , OQuan.y, 2*ODan.WIDTH, 2*ODan.HEIGHT);
        g.drawOval(OQuan.x + 5*ODan.WIDTH , OQuan.y,2*ODan.WIDTH, 2*ODan.HEIGHT);
        // vẽ đè lên hình tròn => tạo Ô Quan là một nửa hình tròn
        g.setColor(Color.BLACK);
        g.fillRect(OQuan.x + OQuan.WIDTH/2 , OQuan.y, 5*ODan.WIDTH ,2*ODan.HEIGHT);

        //Vẽ Ô Dân
        g.setColor(ODan.oDanColor);
        for (int i = 1; i < 6; i++){
            g.drawRect(OCo.x + i*ODan.WIDTH, OCo.y, ODan.WIDTH, ODan.HEIGHT);
            g.drawRect(OCo.x + i*ODan.WIDTH,OCo.y + ODan.HEIGHT, ODan.WIDTH, ODan.HEIGHT);
        }

        //Vẽ điểm của Ô Dân
//        for (int i = 0; i < 5; i++){
//            g.drawString(  "" + ODan.sumDans(test.oDans.get(i).getDans()), OCo.x + OQuan.WIDTH/2 + i*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2 , OCo.y + 2*ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
//
//        }
//        for (int i = 10; i > 5; i--){
//            g.drawString(  "" + ODan.sumDans(test.oDans.get(i).getDans()), OCo.x + OQuan.WIDTH/2 + (i-6)*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/2, OCo.y + ODan.HEIGHT - (ODan.HEIGHT - Consts.FONT_SIZE + OCo.THICKNESS)/2);
//        }

        //Vẽ điểm ô Quan
//        g.drawString(  "" + test.board[11], OCo.x + OQuan.WIDTH/2 - ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y + OQuan.WIDTH/4 + (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan trái
//        g.drawString(  "" + test.board[5], OCo.x + OQuan.WIDTH/2  + 5*ODan.WIDTH + (ODan.WIDTH - Consts.FONT_SIZE)/4 , OCo.y +  OQuan.WIDTH/4 +  (ODan.HEIGHT - Consts.FONT_SIZE)); // Quan phải

        // Set độ dày cho Chooser
        g2d.setStroke(new BasicStroke(Chooser.THICKNESS));
        // Set màu cho Chooser
        g.setColor(Chooser.chooserColor);
        // Vẽ Chooser
        g.drawRect(Chooser.x, Chooser.y, Chooser.WIDTH, Chooser.HEIGHT);




        if (Chooser.Choosen) {
            // Thiết lập màu cho mũi tên
            g2d.setColor(Arrow.arrowColor);


            // Vẽ đầu mũi tên
            g2d.fillPolygon(arrowL.xPoints, arrowL.yPoints, 3);
            g2d.fillPolygon(arrowR.xPoints, arrowR.yPoints, 3);
        }


    }


    @Override public void actionPerformed(ActionEvent e) {
        Scanner sc = new Scanner(System.in);

        OQuan quanP = new OQuan(5);
        OQuan quanT = new OQuan(11);
//        test.board[quanP.getIndex()] = quanP.tinhDiem();
//        test.board[quanT.getIndex()] = quanT.tinhDiem();
//        test.playGame();
//        sc.close();
//        Test.nextTurn();

        currentPlayer++;
        repaint();
    }


    @Override public void keyPressed(KeyEvent e) {

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
            if (Chooser.count_y < 0) {
                Chooser.count_y = 1;
            }
            Chooser.y = (Consts.HEIGHT / 2 + 3 * OCo.THICKNESS / 2) - Chooser.count_y * ODan.HEIGHT;
            arrowR.updateArrowPositions();
            arrowL.updateArrowPositions();
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_F) {
            Chooser.count_x += 1;
            if (Chooser.count_x > 2) {
                Chooser.count_x = -2;
            }
            Chooser.x = ((Consts.WIDTH - Chooser.WIDTH) / 2) + Chooser.count_x * ODan.WIDTH;
            arrowR.updateArrowPositions();
            arrowL.updateArrowPositions();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Chooser.count_x -= 1;
            if (Chooser.count_x < -2) {
                Chooser.count_x = 2;
            }
            Chooser.x = ((Consts.WIDTH - Chooser.WIDTH) / 2) + Chooser.count_x * ODan.WIDTH;
            arrowR.updateArrowPositions();
            arrowL.updateArrowPositions();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (Chooser.Choosen){
                Chooser.Choosen = false;
            }
            else if (!Chooser.Choosen){
                Chooser.Choosen = true;
            }

        }
        else if (e.getKeyCode() != KeyEvent.VK_ENTER || e.getKeyCode() != KeyEvent.VK_SPACE) {
            Chooser.Choosen = false;
        }


        if (Chooser.Choosen) {
            Chooser.chooserColor = Color.YELLOW;
        }
        else if (!Chooser.Choosen) {
            Chooser.chooserColor = Color.CYAN;
        }

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

class Test {

    public static Player player1 = TestForTwo.player1;
    public static Player player2 = TestForTwo.player2;
    public static ArrayList<ODan> oDans = TestForTwo.oDans;
    public static ArrayList<Dan> dans = TestForTwo.dans;
    public static ArrayList<OQuan> oQuans = TestForTwo.oQuans;
    public static ArrayList<Quan> quans = TestForTwo.quans;


}

