package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class TicketPackage {
    private String packageId;  // Unique ID for the ticket package
    private String packageType;  // Package type (e.g., VIP, Normal)
    private double price;  // Price for tickets in this package
    private VendorEntity vendor;  // Vendor offering this ticket package
    private EventEntity event;// Event this package is associated with
    private List<TicketEntity> tickets;
}
