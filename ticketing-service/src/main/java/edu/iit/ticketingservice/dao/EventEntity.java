package edu.iit.ticketingservice.dao;

import edu.iit.ticketingservice.dto.Vendor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendor;  // Vendor offering this ticket package

    @OneToMany(mappedBy = "event")  // Matches 'event' field in TicketPackageEntity
    private List<TicketPackageEntity> ticketPackages;   // List of ticket packages for this event

}
