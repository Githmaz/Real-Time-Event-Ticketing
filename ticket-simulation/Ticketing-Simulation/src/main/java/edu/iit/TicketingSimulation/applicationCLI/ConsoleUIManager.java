package edu.iit.ticketingservice.applicationCLI;

public class ConsoleUIManager {

    protected static void displayTitle(String title) {
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
    protected static void displayMainMenu(){
        System.out.println("\nMain Menu");
        System.out.println("\t1) Configure Ticketing");
        System.out.println("\t2) Configure Database");
        System.out.println("\t3) Start System");
        System.out.println("\t4) Monitor System");
        System.out.println("\t5) Stop System");
        System.out.println("\t6) Update Admin Credentials");
        System.out.println("\t7) Exit");
    }
    protected static void displayDatabaseConfigMenu() {
        System.out.println("\nDatabase Configuration Menu");
        System.out.println("\t1) Edit All Configurations");
        System.out.println("\t2) Update Database URL");
        System.out.println("\t3) Update Username");
        System.out.println("\t4) Update Password");
        System.out.println("\t5) Return to Main Menu");
    }


    protected static void displayTicketConfigMenu() {
        System.out.println("\nTicketing Configuration Menu");
        System.out.println("\t1) Edit All Configurations");
        System.out.println("\t2) Update Total Tickets");
        System.out.println("\t3) Update Ticket Release Rate");
        System.out.println("\t4) Update Customer Retrieval Rate");
        System.out.println("\t5) Update Max Ticket Capacity");
        System.out.println("\t6) Return to Main Menu");
    }


}
