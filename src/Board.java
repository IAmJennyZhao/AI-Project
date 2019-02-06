import java.util.ArrayList;

public abstract class Board<E extends Comparable<E>> {
    protected E[][] theBoard;

    /**
     * Constructs a two-dimensional board based off row and col
     * @param row the number of rows
     * @param col the number of cols
     */
    public Board(int row, int col) {
        theBoard =  (E[][]) new Comparable[row][col];
    }

    /**
     * Sets a cell of the board
     * @param row the row
     * @param col the col
     * @param value the value to set at row, col
     */
    public void set(int row, int col, E value) {
        theBoard[row][col] = value;
    }

    /**
     * Get the value at the given location
     * @param row the row
     * @param col the column
     * @return the value at the given location
     */
    public E get(int row, int col) {
        return theBoard[row][col];
    }

    /**
     * If the pieces has a streak that is len long in the given row,
     * return the coordinates that make the streak, otherwise return null.
     * @param row the row to look for the streak
     * @param piece the piece in the streak
     * @param len the length of the streak
     * @return the coordinates [row,col] that make the streak, or null if no streak exists
     */
    public int[] streakInRow(int row, Comparable piece, int len) {
        if(len==1){
            for(int c = 0; c<theBoard[0].length-1;c++){
                if(theBoard[row][c].equals(piece)) {
                    int[] coordinate = {row, c};
                    return coordinate;
                }
            }
            return null;
        }
        int times = 1;

        for(int c = 0; c < theBoard[0].length - 1; c++) {
            if(theBoard[row][c].equals(theBoard[row][c+1]) && theBoard[row][c].equals(piece)) {
                times++;

                if (times == len) {
                    int[] coordinate = {row,c-len+1};
                    return coordinate;
                }
            }
            else
                times = 1;
        }

        return null;
    }

    /**
     * If the pieces has a streak that is len long in the given col,
     * return the coordinates the make the streak, otherwise return null.
     * @param col the col to look for the streak
     * @param piece the piece in the streak
     * @param len the length of the streak
     * @return the coordinates [row,col] that make the streak, or null if no streak exists
     */
    public int[] streakInCol(int col, Comparable piece, int len) {
        if(len==1){
            for(int r = 0; r<theBoard.length-1; r++){
                if(theBoard[r][col].equals(piece)) {
                    int[] coordinate = {r,col};
                    return coordinate;
                }
            }
            return null;
        }
        int times = 1;

        for(int r = 0; r < theBoard.length - 1; r++) {
            if(theBoard[r][col].equals(theBoard[r+1][col]) && theBoard[r][col].equals(piece)) {
                times++;
                if (times == len) {
                    int[] coordinate = {r-len+1,col};
                    return coordinate;
                }
            }
            else
                times = 1;
        }

        return null;
    }

    /**
     * If there is a streak that is count long in NE diagonal starting
     * at the given row and col position, the coordinates that make the first streak
     * are returned. Otherwise, null is returned.
     * @param startRow the starting row
     * @param startCol the starting col
     * @param piece the piece in the streak
     * @param len the length of the streak
     * @return the coordinates that make the streak, or null if no streak exists
     */
    public int[] streakInNorthEastDiag(int startRow, int startCol, Comparable piece, int len) {

        int times = 1;
        int row = startRow;
        int col = startCol;

        if(len==1){
            while(row>0&&col<theBoard[0].length){
                if(theBoard[row][col].equals(piece)){
                    int[] coordinate = {row,col};
                    return coordinate;
                }
                row--;
                col++;
            }
            return null;
        }

        while(row > 0 && col < theBoard[0].length - 1) {
            if(theBoard[row][col].equals(theBoard[row-1][col+1]) && theBoard[row][col].equals(piece)) {
                times++;

                if (times == len) {
                    int[] coordinate = {row+len-1,col-len+1};
                    return coordinate;
                }
            } else
                times = 1;

            row--;
            col++;
        }

        return null;
    }

    /**
     * If there is a streak that is count long in NE diagonal starting
     * at the given row and col position, the value that makes the first streak
     * is returned. Otherwise, null is returned.
     * @param startRow the starting row
     * @param startCol the starting col
     * @param piece the piece in the streak
     * @param len the length of the streak
     * @return the coordinates that make the streak, or null if no streak exists
     */
    public int[] streakInSouthEastDiag(int startRow, int startCol, Comparable piece, int len) {
        int times = 1;
        int row = startRow;
        int col = startCol;
        if(len==1) {
            while (row < theBoard.length - 1 && col < theBoard[0].length-1){
                if(theBoard[row][col].equals(piece)){
                    int[] coordinate = {row,col};
                    return coordinate;
                }
                row++;
                col++;
            }
            return null;
        }
        while(row < theBoard.length - 1 && col < theBoard[0].length - 1) {
            if(theBoard[row][col].equals(theBoard[row+1][col+1]) && theBoard[row][col].equals(piece)) {
                times++;

                if (times == len) {
                    int[] coordinate = {row - len + 1, col - len + 1};
                    return coordinate;
                }
            }else
                times = 1;

            row++;
            col++;
        }

        return null;
    }

    /**
     * Return a string representation of the board
     * @return a string representation of the board
     */
    public String toString() {
        String toReturn = "";
        int longestLen = (""+theBoard[theBoard.length-1][theBoard[0].length-1]).length();

        for(int r = 0; r < theBoard.length; r++) {
            for(int c = 0; c < theBoard[0].length; c++) {
                String temp = "" +theBoard[r][c];
                int len = temp.length();
                for(int l = len; l<longestLen; l++){
                    temp = temp + " ";
                }
                toReturn += temp;
            }

            toReturn += "\n";
        }

        return toReturn;
    }

    /**
     * Returns the number of rows of the board
     * @return the number of rows of the board
     */
    public int getRows() {
        return theBoard.length;
    }

    /**
     * Returns the number of cols of the board
     * @return the number of cols of the board
     */
    public int getCols() {
        return theBoard[0].length;
    }

    // Abstract Methods
    public abstract void placePiece(String loc, String player);
    public abstract void retractPiece(String loc);
    public abstract ArrayList<String> getEmptyLocs();
    public abstract boolean isWinner(String player);
    public abstract boolean isGameOver();
}
