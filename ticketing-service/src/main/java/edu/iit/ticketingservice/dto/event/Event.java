package edu.iit.ticketingservice.dto.event;

import edu.iit.ticketingservice.dao.TicketPackageEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Event {
    private String eventId;

    @NotBlank(message = "Event Name cannot be blank")
    private String eventName;

    @NotNull(message = "Event Date and Time cannot be null")
    private LocalDateTime eventDateTime; // Combined date and time for the event

    private LocalDateTime eventCreatedDate; // Automatically set, no validation needed

    @NotBlank(message = "Location cannot be blank")
    private String location; // Event location

    private String vendorId; // Vendor offering this event

    private List<TicketPackageEntity> ticketPackages; // List of ticket packages for this event

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setTicketPackages(List<TicketPackageEntity> ticketPackages) {
        this.ticketPackages = ticketPackages;
    }

    public @NotNull(message = "Event Date and Time cannot be null") LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(@NotNull(message = "Event Date and Time cannot be null") LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public LocalDateTime getEventCreatedDate() {
        return eventCreatedDate;
    }

    public void setEventCreatedDate(LocalDateTime eventCreatedDate) {
        this.eventCreatedDate = eventCreatedDate;
    }

    public @NotBlank(message = "Location cannot be blank") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Location cannot be blank") String location) {
        this.location = location;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public List<TicketPackageEntity> getTicketPackages() {
        return ticketPackages;
    }
}
