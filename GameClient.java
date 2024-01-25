import java.rmi.Naming;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        try {
            GameInterface game = (GameInterface) Naming.lookup("rmi://localhost/GameService");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            boolean joined = game.joinGame(playerName);

            if (joined) {
                System.out.println("Joined the game!");

                while (!game.isGameOver()) {
                    // Display the current board
                    System.out.println(game.getCurrentBoard());

                    // Make a move
                    int row, col;
                    do {
                        System.out.println("Enter your move (row and column, separated by space): ");
                        row = getValidInput("Enter the row (0, 1, or 2): ", scanner);
                        col = getValidInput("Enter the column (0, 1, or 2): ", scanner);
                    } while (!game.makeMove(row, col, playerName));

                    // Check for game over
                    if (game.isGameOver()) {
                        System.out.println("Game over!");
                    }
                }
            } else {
                System.out.println("The game is full. Cannot join.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static int getValidInput(String prompt, Scanner scanner) {
        int input = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input <= 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 0 and 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
        return input;
    }
}
