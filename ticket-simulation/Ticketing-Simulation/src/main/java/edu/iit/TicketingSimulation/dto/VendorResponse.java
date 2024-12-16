package edu.iit.TicketingSimulation.dto;

public class VendorResponse {
    private String vendorId;     // Unique ID of the vendor
    private String vendorName;   // Name of the vendor
    private int totalTickets;    // Total number of tickets provided
    private double ticketPrice;  // Price of each ticket

    public VendorResponse() {
    }

    public VendorResponse(String vendorId, String vendorName, int totalTickets, double ticketPrice) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.totalTickets = totalTickets;
        this.ticketPrice = ticketPrice;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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
