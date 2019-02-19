import java.io.IOException;
import java.util.Scanner;

/**
 * Created by huanmeiwu on 2/14/19.
 */
public class C4Human extends Player {
    public C4Human(String inName) {
        super(inName);
    }

    @Override
    public String getMove(Board board, String otherPlayer) {
        System.out.println(board.toString());

        System.out.println("Enter a column to place your piece in (1-7): ");
        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        C4Board c4Board = (C4Board) board;
        int[] emptyRow = c4Board.getEmptyRow();
        Boolean valid = false;
        Integer xd;
        while (!valid) {
            try {
                xd = Integer.parseInt(move);
                if (xd > 7 || xd < 1 || emptyRow[xd - 1] == -1) {
                    System.out.println("Please enter a VALID column to place your piece in (1-7): ");
                    move = in.nextLine();
                } else
                    valid = true;
            } catch (Exception e) {
                System.out.println("Please enter a VALID column to place your piece in (1-7): ");
                move = in.nextLine();
            }
        }
        return move;
    }
}