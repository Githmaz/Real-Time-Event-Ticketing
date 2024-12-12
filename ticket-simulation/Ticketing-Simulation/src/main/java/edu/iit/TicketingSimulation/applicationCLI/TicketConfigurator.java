package edu.iit.TicketingSimulation.applicationCLI;


import edu.iit.TicketingSimulation.dto.SimulationConfig;
import edu.iit.TicketingSimulation.util.SimulationConfigUtill;


import java.util.logging.Logger;

/**
 * TicketConfigurator manages the ticketing configurations of the application.
 * Main Features:
 * - Update all ticket configurations interactively (Total Tickets, Ticket Release Rate, etc.).
 * - Save and load configurations from a JSON file.
 * - Provide interactive menu-driven configuration options.
 */
public class TicketConfigurator {

    private static final Logger logger = Logger.getLogger(TicketConfigurator.class.getName());
    private static final String JSON_FILE_PATH = "src/main/java/edu/iit/TicketingSimulation/config/config.json";

    public static void configureTicketing() {
        while (true) {
            ConsoleUIManager.displayTitle("Ticketing Configuration");
            displayCurrentConfiguration();
            ConsoleUIManager.displayTicketConfigMenu();
            int option = InputUtils.getOptionFromMenu(1, 2);
            switch (option) {
                case 1:
                    updateConfigurations();
                    break;
                case 2:
                    return;
                default:
            }
        }
    }

    private static void updateConfigurations() {
        SimulationConfig config = getOrCreateConfig();

        // Use optional input to update only provided fields
        Integer totalTickets = InputUtils.getOptionalPositiveIntInput("Enter new Total Tickets ");
        Integer ticketReleaseRate = InputUtils.getOptionalPositiveIntInput("Enter new Ticket Release Rate ");
        Integer customerRetrievalRate = InputUtils.getOptionalPositiveIntInput("Enter new Customer Retrieval Rate ");
        Integer maxTicketCapacity = InputUtils.getOptionalPositiveIntInput("Enter new Max Ticket Capacity ");

        if (totalTickets != null) config.setTotalTickets(totalTickets);
        if (ticketReleaseRate != null) config.setTicketReleaseRate(ticketReleaseRate);
        if (customerRetrievalRate != null) config.setCustomRetrievalRate(customerRetrievalRate);
        if (maxTicketCapacity != null) config.setMaxTicketCapacity(maxTicketCapacity);

        SimulationConfigUtill.saveConfig(config);
    }
    private static SimulationConfig getOrCreateConfig() {
        SimulationConfig config = SimulationConfigUtill.readConfig();
        if (config == null) {
            throw new IllegalStateException("No existing configuration found. Ensure a valid configuration file is present.");
        }
        return config;
    }


    private static void displayCurrentConfiguration() {
        SimulationConfig config = SimulationConfigUtill.readConfig();
        if (config == null) {
            logger.info("No existing configuration found. Starting with default values.");
        } else {
            System.out.println("\n--- Current Ticketing Configuration ---");
            System.out.println("\tTotal Tickets: " + config.getTotalTickets());
            System.out.println("\tTicket Release Rate: " + config.getTicketReleaseRate());
            System.out.println("\tCustomer Retrieval Rate: " + config.getCustomRetrievalRate());
            System.out.println("\tMax Ticket Capacity: " + config.getMaxTicketCapacity());
            System.out.println("---------------------------------------\n");
        }
    }
}
