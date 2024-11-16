package Player;

public class Player {
    private String name;
    private /*static*/ int index = 0;
    private /*static*/ int score = 0;
    private /*static*/ int diemCong = 0;

    //Constructor
    public Player(String name) {
        this.name = name;
        this.index += 1;
    }

    //Getter
    public String getName() {
        return name;
    }
    public int getIndex() {
        return index;
    }
    public int getScore() {
        return score;
    }
    public int getDiemCong() {
        return diemCong;
    }


    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setDiemCong(int diemCong) {
        this.diemCong = diemCong;
    }
}
