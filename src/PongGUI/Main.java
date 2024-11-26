package PongGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {
    private ControlWindow cw = new ControlWindow();


    public Main(){
        this.add(cw);
        this.pack();
        this.setTitle("O An Da.Quan");
        this.setSize(Consts.WIDTH, Consts.HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

class ControlWindow extends JPanel implements ActionListener, KeyListener {
    private Ball ball = new Ball(Consts.WIDTH /2 , Consts.HEIGHT / 2, 30);
    private Timer timer = new Timer(10, this);
    private Player lp = new Player(0, Consts.HEIGHT /2);
    private Player rp = new Player(Consts.WIDTH - lp.with - lp.with/2, Consts.HEIGHT /2 );
    private Font gameFont = new Font("Press Start 2P" , Font.PLAIN, 30);


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE); //set màu cho mọi thứ
//        g.drawRect(100,100,100,100); //vẽ viền hình chữ nhật
//        g.fillRect(100,100,100,100); //Vẽ lấp đầy hin chữ nhật
//        g.fillOval(200,200,100,100);

        g.fillOval(ball.x, ball.y, ball.diameter,ball.diameter);

//        g.setColor(Color.BLUE);
        g.fillRect(lp.x, lp.y, lp.with, lp.height); //vẽ người chơi bên trái
        g.fillRect(rp.x, rp.y, rp.with, rp.height);

        g.setColor(Color.GREEN);
        g.setFont(gameFont);
        g.drawString("LEFT: " + lp.score, 0, 40);
        g.drawString( rp.score + ": RIGHT", Consts.WIDTH - 250 - 10, 40);
    }


    @Override public void actionPerformed(ActionEvent e) {
//        System.out.println("a");
        ball.x += ball.speedX;
        ball.y += ball.speedY;

        Rectangle reactBall = new Rectangle(ball.x, ball.y, ball.diameter, ball.diameter);
        Rectangle rectLp = new Rectangle(lp.x, lp.y, lp.with, lp.height);
        Rectangle rectRp = new Rectangle(rp.x, rp.y, rp.with, rp.height);


        if (reactBall.intersects(rectRp)) {
            ball.speedX = -Math.abs(ball.speedX);
        }
        if (reactBall.intersects(rectLp)) {
            ball.speedX = Math.abs(ball.speedX);
        }

        if (ball.y >= Consts.HEIGHT - ball.diameter*2 || ball.y <= 0) {
            ball.speedY *= -1;
        }

        if (ball.x >= Consts.WIDTH - ball.diameter) {
//            ball.speedX *= -1;
            lp.score ++;
            ball.x = Consts.WIDTH / 2;
            ball.y = Consts.HEIGHT / 2;
        }
        if (ball.x <= 0){
//            ball.speedX *= -1;
            rp.score ++;
            ball.x = Consts.WIDTH / 2;
            ball.y = Consts.HEIGHT / 2;
        }

        repaint();
    }


    @Override public void keyPressed(KeyEvent e) {
        //Nhấn vào là nhận rồi

        //W,S
        if (e.getKeyCode() == KeyEvent.VK_W) {
            lp.y  -= lp.speedY;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            lp.y += lp.speedY;
        }

        //Mũi tên LEN, XUONG
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            rp.y -= rp.speedY;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            rp.y += rp.speedY;
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

class Ball {
    public int x,y ,diameter;
    public int speedX = 5;
    public int speedY = 5;

    public Ball(int x, int y, int diameter){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }
}

class Player {
    public int x,y;
    public int with = 20, height = 100;
    public int speedY = 13;
    public int score = 0;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Consts {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;
}