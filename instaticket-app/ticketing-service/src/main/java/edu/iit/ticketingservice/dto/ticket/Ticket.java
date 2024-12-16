package edu.iit.ticketingservice.dto.ticket;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String ticketId;
    private String ticketType;
    private boolean available;
    private EventEntity event;
    private CustomerEntity customer;
    private TicketPackageEntity ticketPackage;
    private LocalDateTime soldDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.setTicketId(this.ticketPackage.getPackageId()+"T-"+this.id);
    }
}
