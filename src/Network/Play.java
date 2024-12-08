package Network;

import java.util.Scanner;

public class Play {
    int a = 1;
    int b = 2;
    int c = 0;


    public static void main(String[] args) {
        while (true){
            Scanner sc = new Scanner(System.in);
            Play p = new Play();
            p.c = sc.nextInt();
            if (p.c == p.a ) {
                Server.Z();
            }
            else if (p.c == p.b) {
                Client2.Z();
            }

        }

//        System.out.println(p.c);
    }
}
