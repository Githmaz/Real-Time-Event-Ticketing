package edu.iit.ticketingservice.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class TicketEntity {
    @Id
    private String ticketId;  // Unique ID for each ticket

    @Column(nullable = false)
    private String ticketType;  // Ticket type (e.g., VIP, Normal)

    @Column(nullable = false)
    private boolean available;  // Ticket availability status

    @ManyToOne
    private EventEntity event;  // Event associated with this ticket

    @ManyToOne
    private CustomerEntity customer;  // The customer who purchased the ticket

    @ManyToOne
    private TicketPackageEntity ticketPackage;  // Ticket package this ticket belongs to
}
