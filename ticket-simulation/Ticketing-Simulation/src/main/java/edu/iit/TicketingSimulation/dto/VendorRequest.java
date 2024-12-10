package edu.iit.TicketingSimulation.dto;

public class VendorRequest {
    private String vendorName;   // Name of the vendor
    private int totalTickets;    // Total number of tickets the vendor will provide
    private double ticketPrice;  // Price of each ticket

    public VendorRequest() {
    }

    public VendorRequest(String vendorName, int totalTickets, double ticketPrice) {
        this.vendorName = vendorName;
        this.totalTickets = totalTickets;
        this.ticketPrice = ticketPrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
