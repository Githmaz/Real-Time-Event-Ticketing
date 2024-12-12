package edu.iit.TicketingSimulation.applicationCLI;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    // Method to get an integer option within a range
    public static int getOptionFromMenu(int minOption, int maxOption) {
        while (true) {
            int option = getPositiveIntInput("\nPlease select an option -> ");
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

    // Method to get a valid double Positive input
    public static double getPositiveDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Method to get a valid Positive integer input
    public static int getPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if(value < 0){
                    System.out.println("Please enter a Positive Value ");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // method to get optional Positive integer input
    public static Integer getOptionalPositiveIntInput(String prompt) {
        while (true) {
            System.out.print(prompt + "(Press Enter to skip) : ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null; // User pressed Enter, return null to indicate skipped
            }
            try {
                 int value =  Integer.parseInt(input);
                 if(value < 0){
                     System.out.println("Please enter a Positive Value .");
                     continue;
                 }
                 return value;
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
    // Method to get a boolean input using T or F
    public static boolean getBooleanInput(String prompt) {
        System.out.print(prompt + " (T/F): ");
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("T")) {
                return true;
            } else if (input.equals("F")) {
                return false;
            } else {
                System.out.println("Please enter 'T' for true or 'F' for false.");
            }
        }
    }
}
