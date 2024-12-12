package edu.iit.TicketingSimulation.service;

import edu.iit.TicketingSimulation.dto.CustomerRequest;
import edu.iit.TicketingSimulation.dto.SimulationConfig;
import edu.iit.TicketingSimulation.dto.SimulationInitData;
import edu.iit.TicketingSimulation.dto.VendorRequest;

import java.util.List;

public interface SimulationService {
    /**
     * Initializes the simulation with specified parameters.
     *
     * @param customerCount      Number of customers.
     * @param vendorCount        Number of vendors.
     * @param ticketsPerCustomer Number of tickets each customer will retrieve.
     * @param vipCustomerCount   Number of VIP customers.
     * @param simulationConfig   Custom simulation configuration or null for default.
     */
    void initializeSimulation(int customerCount, int vendorCount, int ticketsPerCustomer, int vipCustomerCount, SimulationConfig simulationConfig);

    /**
     * Initializes the simulation with lists of CustomerRequest and VendorRequest objects.
     *
     * @param customerRequests List of CustomerRequest objects.
     * @param vendorRequests   List of VendorRequest objects.
     */
    void initializeSimulationWithRequests(List<CustomerRequest> customerRequests, List<VendorRequest> vendorRequests);

    /**
     * Dynamically adds a new customer to the simulation.
     *
     * @param customerRequest CustomerRequest containing customer details.
     */
    void addCustomer(CustomerRequest customerRequest);

    /**
     * Dynamically adds a new vendor to the simulation.
     *
     * @param vendorRequest VendorRequest containing vendor details.
     */
    void addVendor(VendorRequest vendorRequest);

    /**
     * Starts the simulation by running all customer and vendor threads.
     */
    void startSimulation();

    /**
     * Stops the simulation and interrupts all active threads.
     */
    void stopSimulation();

    /**
     * Retrieves the status of the simulation.
     *
     * @return A string representing the current simulation status.
     */
    String getSimulationStatus();

    /**
     * Retrieves the current simulation configuration.
     *
     * @return SimulationConfig the current simulation configuration.
     */
    SimulationConfig getSimulationConfig();

    /**
     * Saves the current simulation configuration to the configuration file.
     *
     * @param simulationConfig The simulation configuration to be saved.
     */
    void saveSimulationConfig(SimulationConfig simulationConfig);

    /**
     * Checks if the simulation is ready to start.
     *
     * @return SimulationInitData containing the readiness status and details.
     */
    SimulationInitData checkSimulationReady();
}
