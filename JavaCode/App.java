package JavaCode;

import java.util.Scanner;

// normally a new file for abstract classes/interfaces is made
// but in this scenario it feels like unnecessary clutter, and i'm only using one interface
interface ValidationCondition {

    public boolean validate(String input);
}

// this class will contain the main process function
// and helper functions that make it look nice!
public class App {

    private static boolean process = true;
    private static final Scanner SCANNER = new Scanner(System.in); // ide was hinting final keyword, and i agree

    public static void main(String[] args) {

        while (process) {
            // sorry to whoever has to edit this!
            System.out.println(String.format("Welcome to the Student Enrollment System\n%s\nPlease select an option:\n%s\n%s\n%s\n%s\n%s", hr(), "1. Enroll a student", "2. Edit enrolled student", "3. View enrolled students", "4. Exit", hr()));

            // implementation here is kind of naive, but fits all test cases!
            String option = validateInputBasedOnCondition("Select your option: ", input -> {
                return input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4");
            });
            
            // my ide was hinting a "rule switch" case, but i like this implementation compared to tradtional switch case
            switch (option) {
                case "1" -> {
                    System.out.println("You have selected to enroll a student.");
                }
                case "2" -> {
                    System.out.println("You have selected to edit an enrolled student.");
                }
                case "3" -> {
                    System.out.println("You have selected to view enrolled students.");
                }
                case "4" -> {
                    System.out.println("Exiting the system. Goodbye!");
                    process = false;
                }
            }
        }
    }

    // this method takes in a condition and validate it BASED on custom conditions
    // this should make it such that the method can be used for various use cases in the CLI
    // for java, we need to make an interface to pass a lambda function as a parameter
    private static String validateInputBasedOnCondition(String inputString, ValidationCondition condition) {
        System.out.print(inputString);
        String input = SCANNER.nextLine();

        if (condition.validate(input)) {
            return input;
        } else {
            System.out.println("Invalid input, please try again.");
            return validateInputBasedOnCondition(inputString, condition);
        }
    }

    // create a horizontal line for better UI
    // if a length param is not provided, assume 50 characters
    // java doesn't really support optional params, overloading is common alternative
    private static String hr() {
        return "-".repeat(50);
    }

    private static String hr(int length) {
        return "-".repeat(length);
    }
}
