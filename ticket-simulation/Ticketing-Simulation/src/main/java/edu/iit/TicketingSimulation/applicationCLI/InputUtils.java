package edu.iit.TicketingSimulation.applicationCLI;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    // Method to get an integer option within a range
    public static int getOptionFromMenu(int minOption, int maxOption) {
        while (true) {
            int option = getIntInput("\nPlease select an option -> ");
            if (option >= minOption && option <= maxOption) {
                return option;
            } else {
                System.out.printf("Please enter a valid option between %d and %d%n", minOption, maxOption);
            }
        }
    }


    // Method to get a non-empty String input
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input;
        while ((input = scanner.nextLine().trim()).isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
        }
        return input;
    }

    // Method to get a valid double input
    public static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Method to get a valid integer input
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // method to get optional integer input
    public static Integer getOptionalIntInput(String prompt) {
        System.out.print(prompt + "(Press Enter to skip) : ");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null; // User pressed Enter, return null to indicate skipped
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer or press enter to skip.");
            }
        }
    }

    // Method to get optional string input
    public static String getOptionalStringInput(String prompt) {
        System.out.print(prompt + "(Press Enter to skip) : ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input; // Return null if input is empty
    }

}
