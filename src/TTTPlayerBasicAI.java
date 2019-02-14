/**
 * Created by boles on 2/7/2019.
 */
import java.util.ArrayList;
import java.util.Random;
public class TTTPlayerBasicAI extends Player {
    public TTTPlayerBasicAI(String inName) {
        super(inName);
    }

    public String getMove(Board board, String otherPlayer) {
        ArrayList<String> temp = board.getEmptyLocs();
        Random rand = new Random();
        int choice = rand.nextInt(temp.size());
        System.out.println("The computer moved at position: " + (choice + 1));
        return temp.get(choice);
    }
}