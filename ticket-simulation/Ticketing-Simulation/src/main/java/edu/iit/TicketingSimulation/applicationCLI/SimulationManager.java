package edu.iit.TicketingSimulation.applicationCLI;

import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.Vendor;
import edu.iit.TicketingSimulation.simulation.TicketPool;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.SimulationConfigUtill;
import edu.iit.TicketingSimulation.config.TicketConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimulationManager {

    private List<Thread> customerThreads;
    private List<Thread> vendorThreads;
    private TicketPool ticketPool;

    // Custom Initialization: Uses JSON config to initialize the simulation
    public void initializeSimulationFromConfig() {
        TicketConfig config = SimulationConfigUtill.readConfig();
        if (config == null) {
            System.err.println("Failed to load simulation configuration. Please ensure the JSON file exists and is valid.");
            return;
        }

        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();

        ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // Create customer threads from config
        for (int i = 1; i <= config.getTotalTickets(); i++) {
            Customer customer = new Customer(
                    "Customer-" + i,
                    false,
                    config.getCustomRetrievalRate(),
                    ticketPool,
                    2 // Number of tickets per customer (can be part of config if needed)
            );
            Thread customerThread = new Thread(customer, "Customer-Thread-" + i);
            customerThreads.add(customerThread);
        }

        // Create vendor threads from config
        for (int i = 1; i <= config.getTicketReleaseRate(); i++) {
            Vendor vendor = new Vendor(
                    "Vendor-" + i,
                    3,
                    2,
                    ticketPool,
                    100.0 // Ticket price (can be part of config if needed)
            );
            Thread vendorThread = new Thread(vendor, "Vendor-Thread-" + i);
            vendorThreads.add(vendorThread);
        }

        System.out.println("Simulation initialized from configuration file.");
    }

    // Automatic Initialization: Minimal inputs, uses default values
    public void initializeSimulation(int customerCount, int vendorCount, int maxTicketCapacity) {
        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();

        if (ticketPool != null) {
            System.err.println("Simulation already initialized.");
            return;
        }

        ticketPool = new TicketPool(maxTicketCapacity);

        // Create customer threads with default values
        for (int i = 1; i <= customerCount; i++) {
            Customer customer = new Customer("Customer-" + i, false, 3, ticketPool, 2);
            Thread customerThread = new Thread(customer, "Customer-Thread-" + i);
            customerThreads.add(customerThread);
        }

        // Create vendor threads with default values
        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor("Vendor-" + i, 3, 2, ticketPool, 100.0);
            Thread vendorThread = new Thread(vendor, "Vendor-Thread-" + i);
            vendorThreads.add(vendorThread);
        }

        System.out.println("Simulation initialized with " + customerCount + " customers and " + vendorCount + " vendors.");
    }

    public void startSimulation() {
        if (ticketPool == null) {
            System.err.println("Simulation not initialized. Please initialize before starting.");
            return;
        }

        System.out.println("Starting simulation...");
        FileLoggerUtil.logToFile("New simulation started.");

        // Start all vendor and customer threads
        vendorThreads.forEach(Thread::start);
        customerThreads.forEach(Thread::start);

        System.out.println("Simulation started.");
        new Scanner(System.in).nextLine();
    }

    public void stopSimulation() {
        if (ticketPool == null) {
            System.err.println("No active simulation to stop.");
            return;
        }

        System.out.println("Stopping simulation...");
        vendorThreads.forEach(thread -> {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        });

        customerThreads.forEach(thread -> {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        });

        ticketPool = null; // Reset the ticket pool
        FileLoggerUtil.logToFile("Simulation stopped.");
        System.out.println("Simulation stopped.");
    }

    public void getSimulationStatus() {
        if (ticketPool == null) {
            System.out.println("Simulation not running.");
        } else {
            System.out.println("Simulation is running with " + customerThreads.size() + " customers and " + vendorThreads.size() + " vendors.");
        }
    }
}
