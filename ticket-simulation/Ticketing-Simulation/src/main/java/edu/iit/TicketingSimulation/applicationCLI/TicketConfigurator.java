package edu.iit.TicketingSimulation.applicationCLI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import edu.iit.TicketingSimulation.config.TicketConfig;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
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
                    logger.info("Returning to Main Menu...");
                    return;
                default:
                    logger.warning("Invalid option selected.");
            }
        }
    }

    private static void updateConfigurations() {
        TicketConfig config = getOrCreateConfig();

        // Use optional input to update only provided fields
        Integer totalTickets = InputUtils.getOptionalIntInput("Enter new Total Tickets");
        Integer ticketReleaseRate = InputUtils.getOptionalIntInput("Enter new Ticket Release Rate");
        Integer customerRetrievalRate = InputUtils.getOptionalIntInput("Enter new Customer Retrieval Rate");
        Integer maxTicketCapacity = InputUtils.getOptionalIntInput("Enter new Max Ticket Capacity");

        if (totalTickets != null) config.setTotalTickets(totalTickets);
        if (ticketReleaseRate != null) config.setTicketReleaseRate(ticketReleaseRate);
        if (customerRetrievalRate != null) config.setCustomRetrievalRate(customerRetrievalRate);
        if (maxTicketCapacity != null) config.setMaxTicketCapacity(maxTicketCapacity);

        saveJsonConfig(config);
        logger.info("Configurations updated successfully.");
    }

    private static TicketConfig getOrCreateConfig() {
        TicketConfig config = readJsonConfig();
        if (config == null) {
            config = new TicketConfig(); // Create a new config if none exists
            logger.info("No existing configuration found. Starting with default values.");
        }
        return config;
    }

    private static void saveJsonConfig(TicketConfig config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            gson.toJson(config, writer);
            logger.info("Configuration saved successfully in JSON.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to save the JSON configuration: " + e.getMessage());
        }
    }

    private static TicketConfig readJsonConfig() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            return gson.fromJson(reader, TicketConfig.class); // Deserialize JSON into TicketConfig object
        } catch (IOException | JsonSyntaxException e) {
            logger.log(Level.SEVERE, e, () -> "Failed to read the JSON configuration: " + e.getMessage());
            return null; // Return null if reading fails
        }
    }

    private static void displayCurrentConfiguration() {
        TicketConfig config = readJsonConfig();
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
