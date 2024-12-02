package GameGUI;

import OCo.OCo;
import OCo.ODan;

import java.awt.*;

public class Arrow {
    public static Color arrowColor = Color.ORANGE;

    // Tạo đầu mũi tên
    public static int arrowWidth = (ODan.WIDTH)/10;  // Độ rộng của đầu mũi tên
    public static int arrowHeight = (ODan.HEIGHT*16)/100; // Độ cao của đầu mũi tên

    public int x = Chooser.x + ODan.WIDTH - arrowHeight/2 ;
    public int y = Chooser.y + ODan.WIDTH/2 - arrowWidth ;

    public String direction = " ";
    public boolean choosen;

//    public int count_x = 0;
//    public int count_y = 0;

    // Tính toán điểm đầu mũi tên
    public int[] xPoints /*= {this.x, this.x  - arrowWidth, this.x - arrowWidth}*/;
    public int[] yPoints /*= {this.y, this.y - arrowHeight, this + arrowHeight}*/;



    public Arrow(String direction) {
        this.direction = direction;
        if (direction.equals("t")) {
            x = Chooser.x + ODan.WIDTH - arrowHeight/2;
            y = Chooser.y + ODan.WIDTH/2 - arrowWidth ;

            this.x -= ODan.WIDTH + Chooser.THICKNESS/4;
            this.xPoints = new int[]{this.x, this.x + arrowWidth, this.x + arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }
        else if (direction.equals("p")) {
            this.xPoints = new int[]{this.x, this.x - arrowWidth, this.x - arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }


    }
    public void updateArrowPositions() {
        this.x = Chooser.x + ODan.WIDTH - arrowHeight/2 ;
        this.y = Chooser.y + ODan.WIDTH/2 - arrowWidth ;
        // Cập nhật tọa độ các đỉnh mũi tên
        if (this.direction.equals("t")) {
            x = Chooser.x + ODan.WIDTH - arrowHeight/2;
            y = Chooser.y + ODan.WIDTH/2 - arrowWidth ;

            this.x -= ODan.WIDTH + Chooser.THICKNESS/4;
            this.xPoints = new int[]{this.x, this.x + arrowWidth, this.x + arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }
        else if (this.direction.equals("p")) {
            this.xPoints = new int[]{this.x, this.x - Arrow.arrowWidth, this.x - Arrow.arrowWidth};
            this.yPoints = new int[]{this.y, this.y - Arrow.arrowHeight, this.y + Arrow.arrowHeight};
        }

    }



}
