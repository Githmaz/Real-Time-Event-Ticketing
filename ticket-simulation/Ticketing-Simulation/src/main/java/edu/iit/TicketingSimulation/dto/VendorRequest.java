package edu.iit.TicketingSimulation.dto;

public class VendorRequest {
    private String userName;   // Name of the vendor
    private int totalTickets;    // Total number of tickets the vendor will provide
    private double ticketPrice;  // Price of each ticket

    public VendorRequest() {
    }

    public VendorRequest(String vendorName, int totalTickets, double ticketPrice) {
        this.userName = vendorName;
        this.totalTickets = totalTickets;
        this.ticketPrice = ticketPrice;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }


    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
