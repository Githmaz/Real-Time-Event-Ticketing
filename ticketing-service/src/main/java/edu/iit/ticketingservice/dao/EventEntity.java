package edu.iit.ticketingservice.dao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


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
    private Date eventDate;

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

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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

    public Date getEventDate() {
        return eventDate;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public List<TicketPackageEntity> getTicketPackages() {
        return ticketPackages;
    }

    @PostPersist
    public void generateEventId() {
        this.setEventId("E" + this.id);
    }
}
