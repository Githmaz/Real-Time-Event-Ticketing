package edu.iit.ticketingservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TicketRequest {
    @NotNull
    private String packageId;

    @Min(1)
    private int ticketCount;

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount( int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public @NotNull String getPackageId() {
        return packageId;
    }

    public void setPackageId(@NotNull String packageId) {
        this.packageId = packageId;
    }

}
