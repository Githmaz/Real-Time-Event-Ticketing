package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class Event {
    private String eventId;
    private String eventName;
    private Date eventDate;
    private String vendorId; // Vendor offering this ticket package
    private List<TicketPackageEntity> ticketPackages;   // List of ticket packages for this event


    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setTicketPackages(List<TicketPackageEntity> ticketPackages) {
        this.ticketPackages = ticketPackages;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getVendorId() {
        return vendorId;
    }

    public List<TicketPackageEntity> getTicketPackages() {
        return ticketPackages;
    }
}
