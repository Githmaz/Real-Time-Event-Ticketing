package edu.iit.ticketingservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true)
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
    private TicketPackageEntity ticketPackage;// Ticket package this ticket belongs to

    @Column(nullable = true)
    private LocalDateTime soldDate;


    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setTicketPackage(TicketPackageEntity ticketPackage) {
        this.ticketPackage = ticketPackage;
    }

    public void setSoldDate(LocalDateTime soldDate) {
        this.soldDate = soldDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public boolean isAvailable() {
        return available;
    }

    public EventEntity getEvent() {
        return event;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public TicketPackageEntity getTicketPackage() {
        return ticketPackage;
    }

    public LocalDateTime getSoldDate() {
        return soldDate;
    }

    @PostPersist
    public void generateEventId() {
        this.setTicketId(this.ticketPackage.getPackageId()+"T"+this.id+"-"+this.ticketType);
    }
}
