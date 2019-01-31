import com.sun.org.apache.xpath.internal.functions.FuncFalse;

public class Driver {
    public static void main(String[] args) {
        Board board = new TTTBoard();
        Player player1 = new TTTHuman("X");
        Player player2 = new TTTHuman("O");
        int turnCount = 1;
        String choice;
        boolean isFinished = false;
        while(!isFinished){
            if(turnCount==1){
                choice = player1.getMove(board);
                board.placePiece(choice, player1.name);
                isFinished = board.isGameOver();
                turnCount =2;
            } else if (turnCount == 2){
                choice = player2.getMove(board);
                board.placePiece(choice, player2.name);
                isFinished = board.isGameOver();
                turnCount = 1;
            }
        }
        if(board.isWinner(player1.name)){
            System.out.println("Player 1 wins!");
        } else if(board.isWinner(player2.name)){
            System.out.println("Player 2 wins!");
        } else{
            System.out.println("It's a tie!");
        }

    }
}
