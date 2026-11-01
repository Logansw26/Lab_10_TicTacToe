
import java.util.Scanner;

public class TicTacToe {

    // Set the board size
    private static final int ROWS = 3;
    private static final int COLS = 3;

    // Create the game board
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean playAgain = true;

        // Keep playing until the users say no
        while (playAgain) {

            clearBoard();

            String player = "X";
            int moves = 0;
            boolean gameOver = false;

            System.out.println("Welcome to Tic Tac Toe!");

            // Keep playing until someone wins or the game is tied
            while (!gameOver) {

                display();

                System.out.println("Player " + player + "'s turn.");

                // Get the row and column
                int row = SafeInput.getRangedInt(input, "Enter row (1-3): ", 1, ROWS);
                int col = SafeInput.getRangedInt(input, "Enter column (1-3): ", 1, COLS);

                // Change the numbers to array positions
                row = row - 1;
                col = col - 1;

                // Check if the move is valid
                while (!isValidMove(row, col)) {

                    System.out.println("That spot is already taken. Try again.");

                    display();

                    row = SafeInput.getRangedInt(input, "Enter row (1-3): ", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(input, "Enter column (1-3): ", 1, COLS) - 1;
                }

                // Place the player's mark on the board
                board[row][col] = player;

                moves++;

                // Check if the player won
                if (isWin(player)) {

                    display();

                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                }

                // Check if the game is tied
                else if (isTie()) {

                    display();

                    System.out.println("The game is a tie!");
                    gameOver = true;
                }

                // Switch players
                if (!gameOver) {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }
            }

            // Ask if the players want to play again
            playAgain = SafeInput.getYNConfirm(input, "Would you like to play again? ");

        }

        System.out.println("Thanks for playing!");

        input.close();
    }

    // Clear the board
    private static void clearBoard() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Display the board
    private static void display() {

        System.out.println();

        for (int row = 0; row < ROWS; row++) {

            System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);

            if (row < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }

        System.out.println();
    }

    // Check if a move is valid
    private static boolean isValidMove(int row, int col) {

        return board[row][col].equals(" ");
    }

    // Check if the player has won
    private static boolean isWin(String player) {

        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    // Check for a row win
    private static boolean isRowWin(String player) {

        for (int row = 0; row < ROWS; row++) {

            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {

                return true;
            }
        }

        return false;
    }

    // Check for a column win
    private static boolean isColWin(String player) {

        for (int col = 0; col < COLS; col++) {

            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {

                return true;
            }
        }

        return false;
    }

    // Check for a diagonal win
    private static boolean isDiagnalWin(String player) {

        // Check diagonal from top left to bottom right
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {

            return true;
        }

        // Check diagonal from top right to bottom left
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {

            return true;
        }

        return false;
    }

    // Check if three spaces are blocked by X and O
    private static boolean isBlocked(String a, String b, String c) {

        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");

        return hasX && hasO;
    }

    // Check if the game is tied
    private static boolean isTie() {

        // Check if the board is completely full
        boolean full = true;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (board[row][col].equals(" ")) {
                    full = false;
                }
            }
        }

        if (full) {
            return true;
        }

        // Check if all rows are blocked
        for (int row = 0; row < ROWS; row++) {

            if (!isBlocked(board[row][0], board[row][1], board[row][2])) {
                return false;
            }
        }

        // Check if all columns are blocked
        for (int col = 0; col < COLS; col++) {

            if (!isBlocked(board[0][col], board[1][col], board[2][col])) {
                return false;
            }
        }

        // Check both diagonals
        if (!isBlocked(board[0][0], board[1][1], board[2][2])) {
            return false;
        }

        if (!isBlocked(board[0][2], board[1][1], board[2][0])) {
            return false;
        }

        return true;
    }
}

