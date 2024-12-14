package edu.iit.TicketingSimulation.applicationCLI;
/**
 * ConsoleUIManager is responsible for managing and displaying user interface components
 * in the console-based Ticketing Simulation system.
 * Main Features:
 * - Display formatted titles in a visually appealing box style.
 * - Provide navigation menus for the main system and specific configurations.
 * - Simplify the process of displaying structured information to the user.
 * This class is utility-focused and only contains static methods. It is not intended to be instantiated.
 */
public class ConsoleUIManager {

    private ConsoleUIManager() {}

    public static void displayTitle(String title) {
        int boxWidth = 40; // Total width of the box

        // Calculate padding to center the title
        int paddingSizeLeft = (boxWidth - title.length()) / 2;
        int paddingSizeRight = boxWidth - title.length() - paddingSizeLeft ;

        String leftPadding = " ".repeat(paddingSizeLeft);
        String rightPadding = " ".repeat(paddingSizeRight);

        // Print box with centered title
        System.out.println("\n\n+" + "-".repeat(boxWidth) + "+");
        System.out.println("|" + leftPadding + title + rightPadding + "|");
        System.out.println("+" + "-".repeat(boxWidth) + "+");
    }
    public static void displayMainMenu(){
        System.out.println("\nMain Menu");
        System.out.println("\t1) Configure Simulation");
        System.out.println("\t2) Configure Database");
        System.out.println("\t3) Simulation System");
        System.out.println("\t4) Exit");
    }
    public static void displayDatabaseConfigMenu() {
        System.out.println("\nDatabase Configuration Menu");
        System.out.println("\t1) Edit  Configurations");
        System.out.println("\t2) Return to Main Menu");
    }


    public static void displayTicketConfigMenu() {
        System.out.println("\nTicketing Configuration Menu");
        System.out.println("\t1) Edit  Configurations");
        System.out.println("\t2) Return to Main Menu");
    }
    public static void displaySimulationManagerMenu() {
        System.out.println("\t1) Start New Simulation");
        System.out.println("\t2) Monitor Simulation Status");
        System.out.println("\t3) Add Customer or Vendor");
        System.out.println("\t4) Stop Simulation");
        System.out.println("\t5) Return to Main Menu");
    }

}
