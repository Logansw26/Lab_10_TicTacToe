
import java.util.Scanner;

public class SafeInput {

    // Get a number within a certain range
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {

        int number = 0;
        boolean valid = false;

        while (!valid) {

            System.out.print(prompt);

            try {
                number = Integer.parseInt(console.nextLine());

                if (number >= low && number <= high) {
                    valid = true;
                } else {
                    System.out.println("Enter a number between " + low + " and " + high);
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }

        return number;
    }

    // Ask the user a yes or no question
    public static boolean getYNConfirm(Scanner console, String prompt) {

        String answer;

        while (true) {

            System.out.print(prompt + "(Y/N): ");

            answer = console.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }
}

