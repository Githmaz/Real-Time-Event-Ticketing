package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import jakarta.persistence.*;

import java.util.List;

public class TicketPackage {
    private String packageId;
    private String packageType;  // Package type (e.g., VIP, Normal)
    private double price;  // Price for tickets in this package
    private VendorEntity vendor;  // Vendor offering this ticket package
    private EventEntity event;// Event this package is associated with
    private int ticketCount;
    private int availableTickets;
    private List<TicketEntity> tickets;

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public void setVendor(VendorEntity vendor) {
        this.vendor = vendor;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }


}
