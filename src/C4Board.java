import java.util.ArrayList;

/**
 * Created by huanmeiwu on 2/8/19.
 */
public class C4Board extends Board{
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private int[] emptyRow = new int[COLS];

    /**
     * Creates a connect4 board that looks like
     * the following when printed:
     *
     * 1234567
     * _______
     * _______
     * _______
     * _______
     * _______
     * _______
     *
     * initializes the board and emptyRow
     */
    public C4Board(){
        super(ROWS, COLS);
        for(int r = 0; r<ROWS; r++){
            for(int c = 0; c<COLS; c++){
                set(r,c, "_");
            }
        }
        for(int c = 0; c<COLS; c++){
            emptyRow[c]=ROWS-1;
        }
    }

    /**
     * places player piece in the column specified
     * @param col column to place move
     * @param player player piece to be placed in column
     */
    @Override
    public void placePiece(String col, String player) {
        int c = Integer.parseInt(col);

        set(emptyRow[c-1],c-1,player);
        emptyRow[c-1] = emptyRow[c-1]-1;
    }

    /**
     * retracts player piece in column specifies
     * @param col column to be removed
     */
    @Override
    public void retractPiece(String col) {
        int c = Integer.parseInt(col);
        int r = emptyRow[c-1]+1;
        set(r,c-1,"_");
    }

    /**
     * Dummy method that fills in the interface
     * @return null
     */
    @Override
    public ArrayList<String> getEmptyLocs() {
        return null;
    }

    /**
     * Method added for Connect4 Board to get the
     * first empty row indexes for each of the columns
     * for example if the board looked like this:
     * 123
     * ___
     * __x
     * _ox
     * _oo
     * x_o
     *
     * method would return {3,1,0}
     * @return int[] with first empty row indexes for each column
     */
    public int[] getEmptyRow() {
        return emptyRow;
    }

    /**
     * Checks if player has won in the board
     * @param player String player to be checked
     * @return True if player has won, false if player has not yet won
     */
    @Override
    public boolean isWinner(String player) {
        if(streakInCol(0, player, 4)!=null)
            return true;
        for(int c = 1; c<COLS; c++){
            if(streakInCol(c, player, 4)!=null || streakInNorthEastDiag(ROWS-1, c, player, 4)!=null || streakInSouthEastDiag(0,c,player,4)!=null)
                return true;
        }
        for(int r = 0; r<ROWS; r++){
            if(streakInRow(r,player, 4)!=null || streakInNorthEastDiag(r,0, player, 4)!=null || streakInSouthEastDiag(r,0,player,4)!=null)
                return true;
        }

        return false;
    }

    /**
     * Checks if the Game is over
     * @return True if game is over, false otherwise
     */
    @Override
    public boolean isGameOver() {
        if(isWinner("X")||isWinner("O"))
            return true;
        for(int r : emptyRow){
            if(r!=-1)
                return false;
        }
        return true;
    }

    /**
     * Overrides toString method to account for the connect4 Board
     * game and rules
     *
     * @return output String
     */
    @Override
    public String toString(){
        String output = "";
        for(int i = 1; i<COLS+1; i++){
            output = output + i;
        }
        for(int r = 0; r<ROWS; r++){
            output = output + "\n";
            for(int c = 0; c< COLS; c++){
                output = output + get(r,c);
            }
        }
        return output;
    }
}
