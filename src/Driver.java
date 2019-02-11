import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import java.util.Random;
public class Driver {
    public static void main(String[] args) {
        Board board = new TTTBoard();
        Player player1 = new TTTHuman("X");
        Player player2 = new TTTPlayerProAI("O");
        Random rand = new Random();
        int turnCount = rand.nextInt(2)+1;
        String choice;
        boolean isFinished = false;
        while(!isFinished){
            if(turnCount==1){
                choice = player1.getMove(board, player2.name);
                board.placePiece(choice, player1.name);
                isFinished = board.isGameOver();

                turnCount =2;
            } else if (turnCount == 2){
                choice = player2.getMove(board, player1.name);
                board.placePiece(choice, player2.name);
                isFinished = board.isGameOver();
                turnCount = 1;
            }
        }
        if(board.isWinner(player1.name)){
            System.out.print(board.toString());
            System.out.println("Player 1 wins!");
        } else if(board.isWinner(player2.name)){
            System.out.print(board.toString());
            System.out.println("Player 2 wins!");
        } else{
            System.out.print(board.toString());
            System.out.println("It's a tie!");
        }

    }
}
