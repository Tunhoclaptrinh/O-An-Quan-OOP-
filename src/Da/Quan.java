package Da;

public class Quan extends Da {
    private static int counter = 1;
    private static final int score = 10;
    private int quan_id;

    //Constructor
    public Quan(int index){
        super(index);
        quan_id = counter;
        counter++;
    }

    //Getter
    public static int getScore(){return score;}


    @Override
    public String toString() {
        return "Quan{" +
                "quan_id=" + quan_id +
                ", index=" + index +
                '}';
    }
}
