import java.util.Scanner;
public class TTTHuman extends Player {

    public TTTHuman(String inName) {
        super(inName);
    }

    public String getMove(Board board, String otherPlayer) {

        System.out.print(board.toString());

        System.out.println("Enter your move(1-9): ");
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        while (!board.getEmptyLocs().contains(choice)) {
            System.out.print("Please enter a valid move");
            choice = s.nextLine();

        }
        return choice;
    }
}