package edu.iit.ticketingservice.dto;

import jakarta.validation.constraints.NotNull;

public class TicketRequest {
    @NotNull
    private String packageId;

    @NotNull
    private String customerId;

    public @NotNull String getPackageId() {
        return packageId;
    }

    public void setPackageId(@NotNull String packageId) {
        this.packageId = packageId;
    }

    public @NotNull String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull String customerId) {
        this.customerId = customerId;
    }
}
