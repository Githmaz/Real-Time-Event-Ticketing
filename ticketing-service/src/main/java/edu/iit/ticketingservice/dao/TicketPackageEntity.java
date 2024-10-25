package edu.iit.ticketingservice.dao;

import edu.iit.ticketingservice.dto.Vendor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TicketPackageEntity {
    @Id
    private String packageId;  // Unique ID for the ticket package

    @Column(nullable = false)
    private String packageType;  // Package type (e.g., VIP, Normal)

    @Column(nullable = false)
    private double price;  // Price for tickets in this package

    @ManyToOne
    private VendorEntity vendor;  // Vendor offering this ticket package

    @ManyToOne
    private EventEntity event;// Event this package is associated with

    @OneToMany(mappedBy = "ticketPackage")  // Matches 'ticketPackage' in TicketEntity
    private List<TicketEntity> tickets;
}
