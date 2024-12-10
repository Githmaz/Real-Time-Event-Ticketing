package edu.iit.TicketingSimulation.dto;

public class CustomerRequest {
    private String fullName; // Full name of the customer
    private boolean isVIP;   // Indicates if the customer is a VIP
    private int numberOfTickets; // Number of tickets the customer wants to purchase

    public CustomerRequest() {
    }

    public CustomerRequest(String fullName, boolean isVIP, int numberOfTickets) {
        this.fullName = fullName;
        this.isVIP = isVIP;
        this.numberOfTickets = numberOfTickets;
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
