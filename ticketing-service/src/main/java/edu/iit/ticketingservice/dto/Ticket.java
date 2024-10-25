package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

public class Ticket {
    private String ticketId;  // Unique ID for each ticket
    private String ticketType;  // Ticket type (e.g., VIP, Normal)
    private boolean available;  // Ticket availability status
    private EventEntity event;  // Event associated with this ticket
    private CustomerEntity customer;  // The customer who purchased the ticket
    private TicketPackageEntity ticketPackage;  // Ticket package this ticket belongs to
}
