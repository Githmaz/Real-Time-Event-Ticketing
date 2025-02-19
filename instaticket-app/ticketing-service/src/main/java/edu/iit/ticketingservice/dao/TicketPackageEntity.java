package edu.iit.ticketingservice.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TicketPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true)
    private String packageId;  // Unique ID for the ticket package

    @Column(nullable = false)
    private String packageType;  // Package type (e.g., VIP, Normal)

    @Column(nullable = false)
    private double price;  // Price for tickets in this package

    @Column(nullable = false)
    private int ticketCount;  // Total tickets in the package

    @Column(nullable = false)
    private int availableTickets; // Tracks available tickets, updated with bookings

    @ManyToOne
    @JsonBackReference
    private EventEntity event;// Event this package is associated with

    @OneToMany(mappedBy = "ticketPackage")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @PostPersist
    public void generatePackageId() {
        this.setPackageId(this.event.getEventId()+"P"+this.id);
    }

}
