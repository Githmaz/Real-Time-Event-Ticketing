package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.util.LoggerConfigurator;

public class AdminControlPanel {

    private final SimulationManager simulationManager = new SimulationManager();

    private void start() {
        while (true) {
            ConsoleUIManager.displayTitle("System");
            ConsoleUIManager.displayMainMenu();
            int option = InputUtils.getOptionFromMenu(1, 7);
            switch (option) {
                case 1:
                    TicketConfigurator.configureTicketing();
                    break;
                case 2:
                    DatabaseConfigurator.configureDatabase();
                    break;
                case 3:
                    startTheSimulation();
                    break;
                case 4:
                    monitorSystem();
                    break;
                case 5:
                    stopTheSimulation();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void startTheSimulation() {
        System.out.println("=== Start The Simulation ===");

        // Gather inputs for the simulation
        int customerCount = InputUtils.getIntInput("Enter the number of customers: ");
        int vendorCount = InputUtils.getIntInput("Enter the number of vendors: ");
        int maxTicketCapacity = InputUtils.getIntInput("Enter the maximum ticket capacity: ");

        // Initialize and start the simulation
        simulationManager.initializeSimulation(customerCount, vendorCount, maxTicketCapacity);
        simulationManager.startSimulation();
    }

    private void stopTheSimulation() {
        System.out.println("=== Stop The Simulation ===");
        simulationManager.stopSimulation();
    }

    private void monitorSystem() {
        System.out.println("=== Monitor System ===");
        System.out.println("Feature not implemented yet.");
    }

    public static void main(String[] args) {
//        LoggerConfigurator.configureGlobalLogger();
        AdminControlPanel adminControlPanel = new AdminControlPanel();
        adminControlPanel.start();
    }
}
