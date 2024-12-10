package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.VendorRequest;
import edu.iit.TicketingSimulation.service.SimulationService;
import edu.iit.TicketingSimulation.model.TicketConfig;


import java.util.Scanner;

/**
 * SimulationManager provides an interactive interface for managing simulations.
 * It delegates core simulation logic to the SimulationService.
 */
public class SimulationManager {

    private final SimulationService simulationService = new SimulationService();

    /**
     * Interactive management of simulations.
     */
    public void manageSimulation() {
        while (true) {
            ConsoleUIManager.displayTitle("Simulation Management");
            ConsoleUIManager.displaySimulationManagerMenu();
            int option = InputUtils.getOptionFromMenu(1, 5);
            switch (option) {
                case 1:
                    startSimulation();
                    break;
                case 2:
                    monitorSimulationStatus();
                    break;
                case 3:
                    addCustomerOrVendor();
                    break;
                case 4:
                    stopSimulation();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void startSimulation() {
        System.out.println("1) Use Default Configuration\n2) Use Custom Configuration");
        int option = InputUtils.getOptionFromMenu(1, 2);

        try {
            int customers = InputUtils.getIntInput("Number of customers: ");
            int vipCustomers = InputUtils.getIntInput("Number of VIP customers: ");
            int ticketsPerCustomer = InputUtils.getIntInput("Tickets per customer: ");
            int vendors = InputUtils.getIntInput("Number of vendors: ");

            if (option == 1) {
                System.out.println("Using default configuration...");
                simulationService.initializeSimulation(customers, vendors, ticketsPerCustomer, vipCustomers,null);
            } else {
                System.out.println("Using custom configuration...");
                int maxCapacity = InputUtils.getIntInput("Max ticket pool capacity: ");
                int customRetrievalRate = InputUtils.getIntInput("Customer retrieval rate: ");
                int ticketReleaseRate = InputUtils.getIntInput("Ticket release rate: ");
                int totalTickets = InputUtils.getIntInput("Total tickets per vendor: ");
                simulationService.initializeSimulation(
                        customers,
                        vendors,
                        ticketsPerCustomer,
                        vipCustomers,
                        new TicketConfig(totalTickets, ticketReleaseRate, customRetrievalRate, maxCapacity)
                );
            }

            System.out.printf(
                    "Simulation initialized with %d customers (%d VIPs), %d vendors, and %d tickets per customer.%n",
                    customers, vipCustomers, vendors, ticketsPerCustomer
            );

            simulationService.startSimulation();
            System.out.println("Simulation started successfully.");
        } catch (Exception e) {
            System.err.println("Error starting simulation: " + e.getMessage());
        }
    }

    private void monitorSimulationStatus() {
        System.out.println("=== Simulation Status ===");
        System.out.println(simulationService.getSimulationStatus());
    }

    private void addCustomerOrVendor() {
        System.out.println("1) Add Customer\n2) Add Vendor");
        int option = InputUtils.getOptionFromMenu(1, 2);

        try {
            if (option == 1) {
                String fullName = InputUtils.getStringInput("Enter customer full name: ");
                boolean isVIP = InputUtils.getBooleanInput("Is the customer VIP (T/F)? ");
                int numberOfTickets = InputUtils.getIntInput("Enter number of tickets for the customer: ");
                simulationService.addCustomer(new CustomerRequest(fullName, isVIP, numberOfTickets));
                System.out.println("Customer added successfully.");
            } else {
                String vendorName = InputUtils.getStringInput("Enter vendor name: ");
                double ticketPrice = InputUtils.getDoubleInput("Enter ticket price: ");
                int totalTickets = InputUtils.getIntInput("Enter total tickets for the vendor: ");
                simulationService.addVendor(new VendorRequest(vendorName, totalTickets, ticketPrice));
                System.out.println("Vendor added successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error adding customer or vendor: " + e.getMessage());
        }
    }
    private void stopSimulation() {
        try {
            simulationService.stopSimulation();
            System.out.println("Simulation stopped successfully.");
        } catch (Exception e) {
            System.err.println("Error stopping simulation: " + e.getMessage());
        }
    }
}
