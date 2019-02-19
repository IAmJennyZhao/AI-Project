import java.util.Random;

/**
 * Created by huanmeiwu on 2/13/19.
 */
public class C4Driver {
    public static void main(String[] args) {
        C4Board board = new C4Board();
        Player p1 = new C4Human("X");
        Player p2 = new C4BasicAI("O");
        Random r = new Random();
        int turnCount = r.nextInt(2);
        Player[] players = {p1, p2};
        String[] name = {"X", "O"};
        while (!board.isGameOver()) {
            String choice = players[turnCount].getMove(board, name[turnCount]);
            board.placePiece(choice, name[turnCount]);
            turnCount = 1 - turnCount;
        }
        if (board.isWinner(p1.name)) {
            System.out.print(board.toString());
            System.out.println("Player 1 wins!");
        } else if (board.isWinner(p2.name)) {
            System.out.print(board.toString());
            System.out.println("Player 2 wins!");
        } else {
            System.out.print(board.toString());
            System.out.println("It's a tie!");
        }
    }
}