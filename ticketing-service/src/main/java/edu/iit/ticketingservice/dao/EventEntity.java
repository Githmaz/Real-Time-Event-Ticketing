package edu.iit.ticketingservice.dao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false, unique = true)
    private String eventId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private LocalDateTime eventDateTime; // Scheduled event date and time

    @Column(nullable = false, updatable = false)
    private LocalDateTime eventCreatedDate; // Automatically set when the event is created

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private VendorEntity vendor;

    @OneToMany(mappedBy = "event")
   @JsonManagedReference
    private List<TicketPackageEntity> ticketPackages;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setVendor(VendorEntity vendor) {
        this.vendor = vendor;
    }

    public void setTicketPackages(List<TicketPackageEntity> ticketPackages) {
        this.ticketPackages = ticketPackages;
    }

    public Long getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public LocalDateTime getEventCreatedDate() {
        return eventCreatedDate;
    }

    public void setEventCreatedDate(LocalDateTime eventCreatedDate) {
        this.eventCreatedDate = eventCreatedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TicketPackageEntity> getTicketPackages() {
        return ticketPackages;
    }

    @PostPersist
    public void generateEventId() {
        this.setEventId("E" + this.id);
    }

    @PrePersist
    public void prePersist() {
        this.eventCreatedDate = LocalDateTime.now(); // Set creation date at the time of persistence
    }
}
