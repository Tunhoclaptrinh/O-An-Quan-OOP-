package Da;

public class Dan extends Da {
    private static int counter = 1;
    private static final int score = 1;
    private int dan_id;
    //Constructor
    public Dan(int index) {
        super(index);
        this.dan_id = counter;
        counter++;
    }

    //Getter
    public static int getScore() {return score;}
    public int getDan_id() {
        this.toString();
        return this.dan_id;
    }

    @Override
    public String toString() {
        return "Dan{" +
                "dan_id=" + dan_id +
                ", index=" + index +
                '}';
    }
}
