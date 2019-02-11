import java.util.ArrayList;

public class TTTPlayerProAI extends Player {

    /**
     * Constucts a new TTTPlayerProAI
     * @param name
     */
    public TTTPlayerProAI(String name) {
        super(name);
    }

    /**
     * Return the move location for the player
     * @param board the game board
     * @return the move location for the player
     */
    public String getMove(Board board, String otherPlayer) {
        MoveInfo mi = recMove((TTTBoard) board, this.name, otherPlayer, 1);
        System.out.println("The computer moved at position: "+mi.getLoc());
        return mi.getLoc();
    }

    /**
     * Performs the MinMax algorithm to determine the
     * move of this AI player.
     * @param board the game board
     * @param playerTurn the player moving
     * @return the MoveInfo for the move
     */
    private MoveInfo recMove(TTTBoard board, String playerTurn, String otherPlayer, int count) {
        ArrayList<String> empty = board.getEmptyLocs();
        MoveInfo recommended = new MoveInfo(empty.get(0), 0);
        if(count ==1) {
            recommended = new MoveInfo(empty.get(0), -1000);
        } else if(count==2){
            recommended = new MoveInfo(empty.get(0), 1000);
        }

        for(String spot: empty){
            TTTBoard board1 = new TTTBoard(board);
            board1.placePiece(spot, playerTurn);
            if(board1.isWinner(playerTurn) && count == 1) {
                return (new MoveInfo(spot, 10));
            }else if(board1.isWinner(playerTurn)&&count==2){
                return (new MoveInfo(spot, -10));

            } else if(board1.isGameOver()){
                return (new MoveInfo(spot, 4));
            } else if(count == 1){
                MoveInfo temp = recMove(board1, otherPlayer, playerTurn, 2);
                if(temp.getScore()>recommended.getScore()){
                    recommended = new MoveInfo(spot, temp.getScore());
                }
            } else if(count ==2){
                MoveInfo temp = recMove(board1, otherPlayer, playerTurn, 1);
                if(temp.getScore()<recommended.getScore()){
                    recommended = new MoveInfo(spot, temp.getScore());
                }

            }

        }

        return recommended;

    }
}