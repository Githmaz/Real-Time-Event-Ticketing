package edu.iit.ticketingservice.dto.event;

import edu.iit.ticketingservice.dao.TicketPackageEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class EventResponse {
    private Long id;
    private String eventId;
    private String eventName;
    private LocalDateTime eventDateTime;
    private LocalDateTime eventCreatedDate;
    private String location;
    private String vendorId;
    private String vendorName;
    private List<TicketPackageEntity> ticketPackages;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
