package edu.iit.TicketingSimulation.util;

public class CLIUtils {

    /**
     * Attempts to clear the terminal screen.
     * Works on Unix/Linux with `clear` and Windows with `cls`.
     * May not work in IDE consoles like IntelliJ.
     */
    public static void clearScreen() {
        try {
            // Determine the operating system
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // For Windows, use `cls`
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix/Linux, use `clear`
                System.out.print("\033[H\033[2J"); // ANSI escape codes for clearing screen
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Failed to clear the screen. " + e.getMessage());
        }
    }
}
