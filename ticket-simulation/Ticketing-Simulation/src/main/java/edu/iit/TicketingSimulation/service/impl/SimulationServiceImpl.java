package edu.iit.TicketingSimulation.service.impl;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.SimulationInitData;
import edu.iit.TicketingSimulation.dto.VendorRequest;
import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.Ticket;
import edu.iit.TicketingSimulation.dto.SimulationConfig;
import edu.iit.TicketingSimulation.model.Vendor;
import edu.iit.TicketingSimulation.model.TicketPool;
import edu.iit.TicketingSimulation.service.LoggerService;
import edu.iit.TicketingSimulation.service.SimulationService;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.SimulationConfigUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SimulationService handles the core logic of initializing, starting, and stopping ticketing simulations.
 * Main Features:
 * - Initialize simulations with default or custom configurations.
 * - Manage simulation threads for customers and vendors.
 * - Log simulation events using FileLoggerUtil.
 */
@Service
public class SimulationServiceImpl  implements SimulationService {

    private SimulationConfig simulationConfig;
    private List<Thread> customerThreads;
    private List<Thread> vendorThreads;
    private TicketPool ticketPool;
    private int vipCustomerCount;

    @Autowired
    private LoggerService loggerService;

    // Constructor to initialize lists
    public SimulationServiceImpl() {
        this.customerThreads = new ArrayList<>();
        this.vendorThreads = new ArrayList<>();
    }

    /**
     * Initializes the simulation with the specified number of customers, vendors, and ticket pool capacity.
     */
    public void initializeSimulation(int customerCount, int vendorCount, int ticketsPerCustomer,int vipCustomerCount, SimulationConfig simulationConfig) {
        Ticket.resetIdCounter();
        if (simulationConfig == null) {
            this.simulationConfig = SimulationConfigUtill.readConfig();
        }else {
            this.simulationConfig = simulationConfig;
        }
        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();
        if(loggerService == null ){
            ticketPool = new TicketPool(this.simulationConfig.getMaxTicketCapacity());
        }
        else {
            ticketPool = new TicketPool(this.simulationConfig.getMaxTicketCapacity(),loggerService);
        }
        this.vipCustomerCount = vipCustomerCount;
        // Create customer threads
        for (int i = 1; i <= customerCount; i++) {
            boolean isVIP = i <= vipCustomerCount; // VIP for first `vipCustomerCount` customers
            Customer customer = new Customer("Customer-" + i, isVIP, this.simulationConfig.getCustomRetrievalRate(), ticketPool, ticketsPerCustomer);
            Thread customerThread = new Thread(customer, "Customer-Thread-" + i);
            customerThreads.add(customerThread);
        }

        // Create vendor threads
        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor("Vendor-" + i, this.simulationConfig.getTotalTickets(), this.simulationConfig.getTicketReleaseRate(), ticketPool, 100.0);
            Thread vendorThread = new Thread(vendor, "Vendor-Thread-" + i);
            vendorThreads.add(vendorThread);
        }
        FileLoggerUtil.initializeNewLogFile();
        FileLoggerUtil.logToFile("Simulation initialized with " + customerThreads.size() + " customers and " + vendorThreads.size() + " vendors.");
    }

    /**
     * Initializes the simulation with lists of CustomerRequest and VendorRequest objects using TicketConfig.
     */
    public void initializeSimulationWithRequests(
            List<CustomerRequest> customerRequests,
            List<VendorRequest> vendorRequests
    ) {
        Ticket.resetIdCounter();
        this.simulationConfig = SimulationConfigUtill.readConfig();

        if (simulationConfig == null) {
            throw new IllegalStateException("Failed to load TicketConfig. Ensure the configuration file is present and valid.");
        }

        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();
        ticketPool = new TicketPool(simulationConfig.getMaxTicketCapacity(),loggerService);
        this.vipCustomerCount = 0;
        // Add customers
        for (CustomerRequest request : customerRequests) {
            if(request.getIsVip()) vipCustomerCount++;
            Customer customer = createCustomerFromRequest(request, simulationConfig);
            Thread customerThread = new Thread(customer, customer.getCustomerId() + "-Thread");
            customerThreads.add(customerThread);
        }

        // Add vendors
        for (VendorRequest request : vendorRequests) {
            Vendor vendor = createVendorFromRequest(request, simulationConfig);
            Thread vendorThread = new Thread(vendor, vendor.getVendorId() + "-Thread");
            vendorThreads.add(vendorThread);
        }
        FileLoggerUtil.initializeNewLogFile();
        FileLoggerUtil.logToFile("Simulation initialized with " + customerRequests.size() + " customers and " + vendorRequests.size() + " vendors.");
    }

    /**
     * Dynamically adds a new customer using CustomerRequest.
     */
    public synchronized void addCustomer(CustomerRequest customerRequest) {
        if(customerRequest.getIsVip()) vipCustomerCount++;
        if (ticketPool == null) {
            throw new IllegalStateException("Simulation not initialized.");
        }
        SimulationConfig simulationConfig = SimulationConfigUtill.readConfig();
        Customer customer = createCustomerFromRequest(customerRequest, simulationConfig);
        Thread customerThread = new Thread(customer, customer.getCustomerId() + "-Thread");
        customerThreads.add(customerThread);
        customerThread.start();

        FileLoggerUtil.logToFile("Added Customer: " + customer.getCustomerId() + " | VIP: " + customerRequest.getIsVip() + " | Tickets: " + customerRequest.getNumberOfTickets());
    }

    /**
     * Dynamically adds a new vendor using VendorRequest.
     */
    public synchronized void addVendor(VendorRequest vendorRequest) {
        if (ticketPool == null) {
            throw new IllegalStateException("Simulation not initialized.");
        }
        SimulationConfig simulationConfig = SimulationConfigUtill.readConfig();
        Vendor vendor = createVendorFromRequest(vendorRequest, simulationConfig);
        Thread vendorThread = new Thread(vendor, vendor.getVendorId() + "-Thread");
        vendorThreads.add(vendorThread);
        vendorThread.start();

        FileLoggerUtil.logToFile("Added Vendor: " + vendor.getVendorId() + " | Tickets: " + vendor.getTotalTickets() + " | Price: $" + vendorRequest.getTicketPrice());
    }


    /**
     * Starts the simulation by running all customer and vendor threads.
     */
    public void startSimulation() {
        checkSimulationReady(); // check the Simulation ready to start if not will throw exception
        FileLoggerUtil.logToFile("New simulation started.");
        vendorThreads.forEach(Thread::start);
        customerThreads.forEach(Thread::start);
    }

    /**
     * Stops the simulation and interrupts all active threads.
     */
    public void stopSimulation() {
        if (ticketPool == null) {
            throw new IllegalStateException("No active simulation to stop.");
        }
        try {
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
        } catch (Exception e) {
            throw new IllegalStateException("Thread interrupted");
        }



        ticketPool = null;
        FileLoggerUtil.logToFile("Simulation stopped.");
    }

    /**
     * Retrieves the status of the simulation.
     */
    public String getSimulationStatus() {
        if (ticketPool == null) {
            return "Simulation not running.";
        }
        return "Simulation is running with " + customerThreads.size() + " customers and " + vendorThreads.size() + " vendors.";
    }

    /**
     * Creates a Customer instance from a CustomerRequest.
     */
    private Customer createCustomerFromRequest(CustomerRequest request, SimulationConfig simulationConfig) {
        int numberOfTickets = (request.getNumberOfTickets() != 0) ? request.getNumberOfTickets() : simulationConfig.getTotalTickets();
        return new Customer(
                request.getUserName(),
                request.getIsVip(),
                simulationConfig.getCustomRetrievalRate(),
                ticketPool,
                numberOfTickets
        );
    }

    /**
     * Creates a Vendor instance from a VendorRequest.
     */
    private Vendor createVendorFromRequest(VendorRequest request, SimulationConfig simulationConfig) {
        int totalTickets = (request.getTotalTickets() != 0) ? request.getTotalTickets() : simulationConfig.getTotalTickets();
        return new Vendor(
                request.getUserName(),
                totalTickets,
                simulationConfig.getTicketReleaseRate(),
                ticketPool,
                request.getTicketPrice()
        );
    }


    /**
     * Retrieves the current simulation configuration.
     * If no configuration is available, throws an exception.
     *
     * @return TicketConfig the current simulation configuration.
     */
    public SimulationConfig getSimulationConfig() {
        SimulationConfig simulationConfig = SimulationConfigUtill.readConfig();
        if (simulationConfig == null) {
            throw new IllegalStateException("Simulation configuration is not available.");
        }
        return simulationConfig;
    }
    /**
     * Saves the current simulation configuration to the configuration file.
     *
     * @param simulationConfig The simulation configuration to be saved.
     */
    public void saveSimulationConfig(SimulationConfig simulationConfig) {
        if (simulationConfig == null) {
            throw new IllegalArgumentException("Ticket configuration cannot be null.");
        }
        SimulationConfigUtill.saveConfig(simulationConfig);
    }
    /**
     * Checks if the simulation is ready to start.
     *
     * @return true if the simulation is ready to start.
     * @throws IllegalStateException if the simulation is not ready with a detailed message.
     */
    public SimulationInitData checkSimulationReady() {
        SimulationConfig simulationConfig;
        try {
            // Attempt to read the simulation configuration
             simulationConfig = SimulationConfigUtill.readConfig();
            if (simulationConfig == null) {
                throw new IllegalStateException("Simulation configuration is missing. Please ensure it is correctly initialized.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read simulation configuration. Error: " + e.getMessage());
        }

        // Check if the ticket pool is initialized
        if (this.ticketPool == null) {
            throw new IllegalStateException("Ticket pool is not initialized. Initialize the ticket pool before starting the simulation.");
        }

        // Check if vendorThreads and customerThreads are null
        if (this.vendorThreads == null || this.customerThreads == null) {
            throw new IllegalStateException("Both vendorThreads and customerThreads are null. Ensure that the simulation is properly initialized.");
        }
        int customerCount = customerThreads.size();
        int vendorCount = vendorThreads.size();

        return new SimulationInitData(customerCount, vendorCount, this.vipCustomerCount, simulationConfig);

    }
}
