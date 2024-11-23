package GameBoard;

import Player.Player;

import java.util.Arrays;

public class testttt {
    private static int[] board = new int[18];
    public static Player player1 = new Player("Player1");
    public static Player player2 = new Player("Player2");
    public static Player player3 = new Player("Player3");


    public static void main(String[] args) {
        Arrays.fill(board, 5);

        System.out.println("               " + board[11]);
        System.out.println("            " + board[12] + "     " + board[10]);
        System.out.println("          " + board[13] + "         " + board[9]);
        System.out.println("        " + board[14] + "             " + board[8]);
        System.out.println("      " + board[15] + "                 " + board[7]);
        System.out.println("    " + board[16] + "                     " + board[6]);
        System.out.println("  " +board[17] + "    " + board[0] + "   " + board[1] + "   " + board[2] + "   " + board[3] + "   " + board[4] + "    " + board[5]);
    }
}
