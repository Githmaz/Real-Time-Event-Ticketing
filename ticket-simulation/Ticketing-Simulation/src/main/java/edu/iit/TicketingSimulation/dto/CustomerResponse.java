package edu.iit.TicketingSimulation.dto;

public class CustomerResponse {
    private String customerId; // Unique ID of the customer
    private String fullName;   // Full name of the customer
    private boolean isVIP;     // Indicates if the customer is a VIP
    private int numberOfTickets; // Number of tickets assigned to the customer

    public CustomerResponse() {
    }

    public CustomerResponse(String customerId, String fullName, boolean isVIP, int numberOfTickets) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.isVIP = isVIP;
        this.numberOfTickets = numberOfTickets;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
