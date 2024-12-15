package GameGUI;

import Model.OCo.ODan;

import java.awt.*;

public class Arrow {

    public static Color Default_arrowColor = Color.ORANGE;
    public Color arrowColor = Default_arrowColor;

    // Tạo đầu mũi tên
    public static int arrowWidth = (ODan.WIDTH)/10;  // Độ rộng của đầu mũi tên
    public static int arrowHeight = (ODan.HEIGHT*16)/100; // Độ cao của đầu mũi tên

    public int x = Chooser.x + ODan.WIDTH - arrowHeight/2 - Chooser.THICKNESS/4;
    public int y = Chooser.y + ODan.WIDTH/2 - arrowWidth;

    public String direction = " ";
    public static boolean isChoosingDirection = false; // Trạng thái chọn chiều
    public static String selectedDirection = "";       // Chiều đã chọn: "left" hoặc "right"


    // Tính toán điểm đầu mũi tên
    public int[] xPoints /*= {this.x, this.x  - arrowWidth, this.x - arrowWidth}*/;
    public int[] yPoints /*= {this.y, this.y - arrowHeight, this + arrowHeight}*/;

    public Arrow(Color arrowColor) {
        this.arrowColor = arrowColor;

    }

    public Arrow(String direction) {
        this.direction = direction;
        if (direction.equals("t")) {
            this.x -= ODan.WIDTH /*+ Chooser.THICKNESS/4*/;
            this.xPoints = new int[]{this.x, this.x + arrowWidth, this.x + arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }
        else if (direction.equals("p")) {
            this.xPoints = new int[]{this.x, this.x - arrowWidth, this.x - arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }
    }


    public void updateArrowPositions() {
        this.x = Chooser.x + ODan.WIDTH - arrowHeight/2 - Chooser.THICKNESS/4;
        this.y = Chooser.y + ODan.WIDTH/2 - arrowWidth ;
        // Cập nhật tọa độ các đỉnh mũi tên
        if (this.direction.equals("t")) {
            this.x -= ODan.WIDTH + Chooser.THICKNESS/4;
            this.xPoints = new int[]{this.x, this.x + arrowWidth, this.x + arrowWidth};
            this.yPoints = new int[]{this.y, this.y - arrowHeight, this.y + arrowHeight};
        }
        else if (this.direction.equals("p")) {
            this.xPoints = new int[]{this.x, this.x - Arrow.arrowWidth, this.x - Arrow.arrowWidth};
            this.yPoints = new int[]{this.y, this.y - Arrow.arrowHeight, this.y + Arrow.arrowHeight};
        }
    }

    public void setArrowColor() {
        if (!this.isChoosingDirection) {
            if (this.direction.equals("t")) {}
            this.arrowColor = Default_arrowColor;
        }
        else {
            this.arrowColor = Color.GREEN; // Đổi màu mũi tên phải để hiển thị đã chọn
        }

    }
}
