package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private String ticketId;
    private String ticketType;
    private boolean available;
    private String eventId;
    private CustomerEntity customer;
    private String ticketPackageId;
    private LocalDateTime soldDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getTicketPackageId() {
        return ticketPackageId;
    }

    public void setTicketPackageId(String ticketPackageId) {
        this.ticketPackageId = ticketPackageId;
    }

    public LocalDateTime getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(LocalDateTime soldDate) {
        this.soldDate = soldDate;
    }
}
