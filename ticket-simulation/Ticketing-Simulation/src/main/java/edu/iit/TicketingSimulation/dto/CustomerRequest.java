package edu.iit.TicketingSimulation.dto;

public class CustomerRequest {
    private String userName; // Full name of the customer
    private boolean isVip;   // Indicates if the customer is a VIP
    private int numberOfTickets; // Number of tickets the customer wants to purchase

    public CustomerRequest() {
    }

    public CustomerRequest(String fullName, boolean isVIP, int numberOfTickets) {
        this.userName = fullName;
        this.isVip = isVIP;
        this.numberOfTickets = numberOfTickets;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getIsVip() {
        return isVip;
    }

    public void setVIP(boolean VIP) {
        isVip = VIP;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
