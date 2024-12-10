package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.util.LoggingServer;

import java.io.IOException;

/**
 * AdminControlPanel serves as the main entry point for managing the system.
 * It provides options to configure ticketing, database, and manage simulations.
 */
public class AdminControlPanel {

    private final SimulationManager simulationManager = new SimulationManager();

    private void start() {
        while (true) {
            ConsoleUIManager.displayTitle("System");
            ConsoleUIManager.displayMainMenu();
            int option = InputUtils.getOptionFromMenu(1, 5);
            switch (option) {
                case 1:
                    TicketConfigurator.configureTicketing();
                    break;
                case 2:
                    DatabaseConfigurator.configureDatabase();
                    break;
                case 3:
                    simulationManager.manageSimulation(); // Delegate simulation management
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static void main(String[] args) {
        LoggingServer.start(); // Start the logging server
        AdminControlPanel adminControlPanel = new AdminControlPanel();
        adminControlPanel.start(); // Start the control panel
    }
}
