package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.VendorRequest;
import edu.iit.TicketingSimulation.service.SimulationService;
import edu.iit.TicketingSimulation.service.impl.SimulationServiceImpl;
import edu.iit.TicketingSimulation.dto.SimulationConfig;

import java.util.logging.Logger;

/**
 * SimulationManager provides an interactive interface for managing simulations.
 * It delegates core simulation logic to the SimulationService.
 */
public class SimulationManager {

    private final SimulationService simulationService = new SimulationServiceImpl();
    private static final Logger logger = Logger.getLogger(SimulationManager.class.getName());
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
                    return;
                default:
                    logger.warning("Invalid option. Please try again.");
            }
        }
    }

    private void startSimulation() {
        System.out.println("1) Use Default Configuration\n2) Use Custom Configuration");
        int option = InputUtils.getOptionFromMenu(1, 2);

        try {
            int customers = InputUtils.getPositiveIntInput("Number of customers: ");
            int vipCustomers = InputUtils.getPositiveIntInput("Number of VIP customers: ");
            int ticketsPerCustomer = InputUtils.getPositiveIntInput("Tickets per customer: ");
            int vendors = InputUtils.getPositiveIntInput("Number of vendors: ");

            if (option == 1) {
                System.out.println("Using default configuration...");
                simulationService.initializeSimulation(customers, vendors, ticketsPerCustomer, vipCustomers,null);
            } else {
                System.out.println("Using custom configuration...");
                int maxCapacity = InputUtils.getPositiveIntInput("Max ticket pool capacity: ");
                int customRetrievalRate = InputUtils.getPositiveIntInput("Customer retrieval rate: ");
                int ticketReleaseRate = InputUtils.getPositiveIntInput("Ticket release rate: ");
                int totalTickets = InputUtils.getPositiveIntInput("Total tickets per vendor: ");
                simulationService.initializeSimulation(
                        customers,
                        vendors,
                        ticketsPerCustomer,
                        vipCustomers,
                        new SimulationConfig(totalTickets, ticketReleaseRate, customRetrievalRate, maxCapacity)
                );
            }

            System.out.printf(
                    "Simulation initialized with %d customers (%d VIPs), %d vendors, and %d tickets per customer.%n",
                    customers, vipCustomers, vendors, ticketsPerCustomer
            );

            simulationService.startSimulation();
            logger.info("Simulation started successfully.");
        } catch (Exception e) {
            logger.warning("Error starting simulation: " + e.getMessage());
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
                int numberOfTickets = InputUtils.getPositiveIntInput("Enter number of tickets for the customer: ");
                simulationService.addCustomer(new CustomerRequest(fullName, isVIP, numberOfTickets));
                logger.info("Customer added successfully.");
            } else {
                String vendorName = InputUtils.getStringInput("Enter vendor name: ");
                double ticketPrice = InputUtils.getPositiveDoubleInput("Enter ticket price: ");
                int totalTickets = InputUtils.getPositiveIntInput("Enter total tickets for the vendor: ");
                simulationService.addVendor(new VendorRequest(vendorName, totalTickets, ticketPrice));
                logger.info("Vendor added successfully.");
            }
        } catch (Exception e) {
            logger.warning("Error adding customer or vendor: " + e.getMessage());
        }
    }
    private void stopSimulation() {
        try {
            simulationService.stopSimulation();
            logger.info("Simulation stopped successfully.");
        } catch (Exception e) {
            logger.warning("Simulation stopped ");
        }
    }
}
