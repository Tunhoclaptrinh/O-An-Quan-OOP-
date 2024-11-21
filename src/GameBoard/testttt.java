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
        for (int i = 0; i < board.length; i++) {
            board[i] = i;
        }
        int n = 7; // Length of each side
        int index = 0;
        System.out.println("       " + board[0]);
        System.out.println("      " + board[1] + "  " + board[17]);
        System.out.println("     " + board[2] + "     " + board[16]);
        System.out.println("    " + board[3] + "        " + board[15]);
        System.out.println("   " + board[4] + "           " + board[14]);
        System.out.println("  " + board[5] + "              " + board[13]);
        System.out.println(board[6] + "  " + board[7] + "  " + board[8] + "  " + board[9] + "  " + board[10] + "  " + board[11] + "  " + board[12]);
    }
}
