package edu.iit.TicketingSimulation.dto;


public class SimulationStartRequest {
    private int customerCount;
    private int vendorCount;
    private int ticketsPerCustomer;
    private int vipCustomerCount;

    // Getters and setters
    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getTicketsPerCustomer() {
        return ticketsPerCustomer;
    }

    public void setTicketsPerCustomer(int ticketsPerCustomer) {
        this.ticketsPerCustomer = ticketsPerCustomer;
    }

    public int getVipCustomerCount() {
        return vipCustomerCount;
    }

    public void setVipCustomerCount(int vipCustomerCount) {
        this.vipCustomerCount = vipCustomerCount;
    }
}
