package edu.iit.ticketingservice.applicationCLI;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TicketConfigurator {

    public static void configureTicketing() {
        while (true) {
            ConsoleUIManager.displayTitle("Ticketing Configuration");
            ConsoleUIManager.displayTicketConfigMenu();
            int option = InputUtils.getOptionFromMenu(1, 6);
            switch (option) {
                case 1:
                    updateAllConfigurations();
                    break;
                case 2:
                    updateTotalTickets();
                    break;
                case 3:
                    updateTicketReleaseRate();
                    break;
                case 4:
                    updateCustomerRetrievalRate();
                    break;
                case 5:
                    updateMaxTicketCapacity();
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updateAllConfigurations() {
        int totalTickets = InputUtils.getIntInput("Enter new Total Tickets: ");
        int ticketReleaseRate = InputUtils.getIntInput("Enter new Ticket Release Rate: ");
        int customerRetrievalRate = InputUtils.getIntInput("Enter new Customer Retrieval Rate: ");
        int maxTicketCapacity = InputUtils.getIntInput("Enter new Max Ticket Capacity: ");
        saveTicketConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    private static void updateTotalTickets() {
        int totalTickets = InputUtils.getIntInput("Enter new Total Tickets: ");
        saveTicketConfig(totalTickets, null, null, null);
    }

    private static void updateTicketReleaseRate() {
        int ticketReleaseRate = InputUtils.getIntInput("Enter new Ticket Release Rate: ");
        saveTicketConfig(null, ticketReleaseRate, null, null);
    }

    private static void updateCustomerRetrievalRate() {
        int customerRetrievalRate = InputUtils.getIntInput("Enter new Customer Retrieval Rate: ");
        saveTicketConfig(null, null, customerRetrievalRate, null);
    }

    private static void updateMaxTicketCapacity() {
        int maxTicketCapacity = InputUtils.getIntInput("Enter new Max Ticket Capacity: ");
        saveTicketConfig(null, null, null, maxTicketCapacity);
    }

    private static void saveTicketConfig(Integer totalTickets, Integer ticketReleaseRate, Integer customerRetrievalRate, Integer maxTicketCapacity) {
        String path = "src/main/resources/application.yml"; // Ensure this path is correct
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            // Load existing YAML data
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(fileInputStream);

            // Get or create 'ticketing' section
            Map<String, Object> ticketingData = (Map<String, Object>) data.getOrDefault("ticketing", new HashMap<>());
            data.put("ticketing", ticketingData);

            // Update configurations if values are provided
            if (totalTickets != null) {
                ticketingData.put("total-tickets", totalTickets);
            }
            if (ticketReleaseRate != null) {
                ticketingData.put("ticket-release-rate", ticketReleaseRate);
            }
            if (customerRetrievalRate != null) {
                ticketingData.put("customer-retrieval-rate", customerRetrievalRate);
            }
            if (maxTicketCapacity != null) {
                ticketingData.put("max-ticket-capacity", maxTicketCapacity);
            }

            // Set YAML formatting options
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setIndent(2);
            options.setPrettyFlow(true);

            // Write updated YAML data back to file
            try (FileWriter fileWriter = new FileWriter(path)) {
                Yaml yamlWriter = new Yaml(options);
                yamlWriter.dump(data, fileWriter);
                System.out.println("Ticketing configuration saved successfully.");
            }

        } catch (IOException e) {
            System.err.println("Failed to save the configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
