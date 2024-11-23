package edu.iit.ticketingservice.dto.ticket;


import java.time.LocalDateTime;

public class TicketResponse {
    private String ticketId;
    private String ticketType;
    private boolean available;
    private String eventName;
    private String eventId;
    private String customerId;
    private String ticketPackageId;
    private LocalDateTime soldDate;


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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
