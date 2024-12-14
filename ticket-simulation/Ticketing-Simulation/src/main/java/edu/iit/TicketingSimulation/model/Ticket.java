package edu.iit.TicketingSimulation.model;

public class Ticket {
    private static long idCounter = 0;
    private String ticketId;
    private String customerId;
    private String vendorId;
    private double price;

    public Ticket(String vendorId, double price) {
        this.ticketId = generateId();
        this.vendorId = vendorId;
        this.price = price;
    }

    // Method to generate ticket ID
    private static synchronized String generateId() {
        return "T-" + (++idCounter);
    }

    public static void resetIdCounter() {
        idCounter = 0;
    }
    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        Ticket.idCounter = idCounter;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
