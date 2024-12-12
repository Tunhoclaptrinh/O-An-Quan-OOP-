package Network;

public class Play {
    int a = 1;
    int b = 2;
    int c = 1;


    public static void startGame() {
        while (true){
            Play p = new Play();
            if (p.c == p.a) {
                Server.Z();
            }
        }

//        System.out.println(p.c);
    }
}
