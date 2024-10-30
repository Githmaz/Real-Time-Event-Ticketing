package edu.iit.ticketingservice.dto;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import jakarta.persistence.*;

import java.util.List;

public class TicketPackageRequest {
    private String packageId;
    private String packageType;
    private double price;
    private int ticketCount;
    private String vendorId;
    private String eventId;


    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
