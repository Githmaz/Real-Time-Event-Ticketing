package edu.iit.TicketingSimulation.dto;

import java.util.List;

public class SimulationRequest {
    private List<CustomerRequest> customers;
    private List<VendorRequest> vendors;

    // Getters and Setters
    public List<CustomerRequest> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerRequest> customers) {
        this.customers = customers;
    }

    public List<VendorRequest> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorRequest> vendors) {
        this.vendors = vendors;
    }
}
