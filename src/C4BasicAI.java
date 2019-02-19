import java.util.Random;

/**
 * Created by huanmeiwu on 2/19/19.
 */
public class C4BasicAI extends Player {
    public C4BasicAI(String inName) {
        super(inName);
    }

    @Override
    public String getMove(Board board, String otherPlayer) {
        System.out.println(board.toString());
        Random r = new Random();
        C4Board c4Board = (C4Board) board;
        int[] emptyRow = c4Board.getEmptyRow();
        Integer move = r.nextInt(7);
        while (emptyRow[move] == -1) {
            move = r.nextInt(7);
        }
        return "" + (move + 1);
    }
}