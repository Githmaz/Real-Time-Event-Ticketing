package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Event {
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private Long vendorId; // Vendor offering this ticket package
    private List<TicketPackageEntity> ticketPackages;   // List of ticket packages for this event

}
