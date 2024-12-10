package edu.iit.TicketingSimulation.service;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.VendorRequest;
import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.Ticket;
import edu.iit.TicketingSimulation.model.TicketConfig;
import edu.iit.TicketingSimulation.model.Vendor;
import edu.iit.TicketingSimulation.simulation.TicketPool;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.SimulationConfigUtill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SimulationService handles the core logic of initializing, starting, and stopping ticketing simulations.
 * Main Features:
 * - Initialize simulations with default or custom configurations.
 * - Manage simulation threads for customers and vendors.
 * - Log simulation events using FileLoggerUtil.
 */
@Service
public class SimulationService {


    private final List<Customer> customers;
    private final List<Vendor> vendors;
    private List<Thread> customerThreads;
    private List<Thread> vendorThreads;
    private TicketPool ticketPool;

    // Constructor to initialize lists
    public SimulationService() {
        this.customers = Collections.synchronizedList(new ArrayList<>());
        this.vendors = Collections.synchronizedList(new ArrayList<>());
        this.customerThreads = new ArrayList<>();
        this.vendorThreads = new ArrayList<>();
    }

    /**
     * Initializes the simulation with the specified number of customers, vendors, and ticket pool capacity.
     */
    public void initializeSimulation(int customerCount, int vendorCount, int ticketsPerCustomer,int vipCustomerCount, TicketConfig ticketConfig) {
        Ticket.resetIdCounter();
        if (ticketConfig == null) {
            ticketConfig = SimulationConfigUtill.readConfig();
        }

        customers.clear();
        vendors.clear();
        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();
        ticketPool = new TicketPool(ticketConfig.getMaxTicketCapacity());
        // Create customer threads
        for (int i = 1; i <= customerCount; i++) {
            boolean isVIP = i <= vipCustomerCount; // VIP for first `vipCustomerCount` customers
            Customer customer = new Customer("Customer-" + i, isVIP, ticketConfig.getCustomRetrievalRate(), ticketPool, ticketsPerCustomer);
            Thread customerThread = new Thread(customer, "Customer-Thread-" + i);
            customerThreads.add(customerThread);
        }

        // Create vendor threads
        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor("Vendor-" + i, ticketConfig.getTotalTickets(), ticketConfig.getTicketReleaseRate(), ticketPool, 100.0);
            Thread vendorThread = new Thread(vendor, "Vendor-Thread-" + i);
            vendorThreads.add(vendorThread);
        }
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
        TicketConfig ticketConfig = SimulationConfigUtill.readConfig();

        if (ticketConfig == null) {
            throw new IllegalStateException("Failed to load TicketConfig. Ensure the configuration file is present and valid.");
        }

        customerThreads = new ArrayList<>();
        vendorThreads = new ArrayList<>();
        ticketPool = new TicketPool(ticketConfig.getMaxTicketCapacity());

        // Add customers
        for (CustomerRequest request : customerRequests) {
            Customer customer = createCustomerFromRequest(request, ticketConfig);
            Thread customerThread = new Thread(customer, customer.getCustomerId() + "-Thread");
            customerThreads.add(customerThread);
        }

        // Add vendors
        for (VendorRequest request : vendorRequests) {
            Vendor vendor = createVendorFromRequest(request, ticketConfig);
            Thread vendorThread = new Thread(vendor, vendor.getVendorId() + "-Thread");
            vendorThreads.add(vendorThread);
        }

        FileLoggerUtil.logToFile("Simulation initialized with " + customerRequests.size() + " customers and " + vendorRequests.size() + " vendors.");
    }

    /**
     * Dynamically adds a new customer using CustomerRequest.
     */
    public synchronized void addCustomer(CustomerRequest customerRequest) {
        if (ticketPool == null) {
            throw new IllegalStateException("Simulation not initialized.");
        }
        TicketConfig ticketConfig = SimulationConfigUtill.readConfig();
        Customer customer = createCustomerFromRequest(customerRequest, ticketConfig);
        Thread customerThread = new Thread(customer, customer.getCustomerId() + "-Thread");
        customerThreads.add(customerThread);
        customerThread.start();

        FileLoggerUtil.logToFile("Added Customer: " + customer.getCustomerId() + " | VIP: " + customerRequest.isVIP() + " | Tickets: " + customerRequest.getNumberOfTickets());
    }

    /**
     * Dynamically adds a new vendor using VendorRequest.
     */
    public synchronized void addVendor(VendorRequest vendorRequest) {
        if (ticketPool == null) {
            throw new IllegalStateException("Simulation not initialized.");
        }
        TicketConfig ticketConfig = SimulationConfigUtill.readConfig();
        Vendor vendor = createVendorFromRequest(vendorRequest, ticketConfig);
        Thread vendorThread = new Thread(vendor, vendor.getVendorId() + "-Thread");
        vendorThreads.add(vendorThread);
        vendorThread.start();

        FileLoggerUtil.logToFile("Added Vendor: " + vendor.getVendorId() + " | Tickets: " + vendor.getTotalTickets() + " | Price: $" + vendorRequest.getTicketPrice());
    }


    /**
     * Starts the simulation by running all customer and vendor threads.
     */
    public void startSimulation() {
        if (ticketPool == null) {
            throw new IllegalStateException("Simulation not initialized.");
        }
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
    private Customer createCustomerFromRequest(CustomerRequest request, TicketConfig ticketConfig) {
        int numberOfTickets = (request.getNumberOfTickets() != 0) ? request.getNumberOfTickets() : ticketConfig.getTotalTickets();
        return new Customer(
                request.getFullName(),
                request.isVIP(),
                ticketConfig.getCustomRetrievalRate(),
                ticketPool,
                numberOfTickets
        );
    }

    /**
     * Creates a Vendor instance from a VendorRequest.
     */
    private Vendor createVendorFromRequest(VendorRequest request, TicketConfig ticketConfig) {
        int totalTickets = (request.getTotalTickets() != 0) ? request.getTotalTickets() : ticketConfig.getTotalTickets();
        return new Vendor(
                request.getVendorName(),
                totalTickets,
                ticketConfig.getTicketReleaseRate(),
                ticketPool,
                request.getTicketPrice()
        );
    }
    /**
     * Retrieves all customers in the simulation.
     */
    public List<Customer> getAllCustomers() {
        if (customers.isEmpty()) {
            throw new IllegalStateException("No customers available.");
        }
        return customers;
    }

    /**
     * Retrieves all vendors in the simulation.
     */
    public List<Vendor> getAllVendors() {
        if (vendors.isEmpty()) {
            throw new IllegalStateException("No vendors available.");
        }
        return vendors;
    }
}
