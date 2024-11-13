package edu.iit.ticketingservice.dto.ticketPackage;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TicketPackageRequest {
    private String packageId;
    @NotBlank(message = "Package type cannot be blank")
    private String packageType;
    @Positive(message = "Price must be greater than 0")
    private double price;
    @Min(value = 1, message = "Ticket count must be at least 1")
    private int ticketCount;
    @NotBlank(message = "Vendor ID cannot be blank")
    private String vendorId;
    @NotBlank(message = "Event ID cannot be blank")
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
