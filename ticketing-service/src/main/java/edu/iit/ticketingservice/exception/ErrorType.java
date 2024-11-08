package edu.iit.ticketingservice.exception;


public enum ErrorType {
    EMAIL_ALREADY_EXISTS("Email already exists"),
    USERNAME_ALREADY_EXISTS("Username already exists"),
    EVENT_NOT_FOUND("Event not found"),
    VENDOR_NOT_FOUND("Vendor not found"),
    PACKAGE_NOT_FOUND("Package not found"),
    CUSTOMER_NOT_FOUND("Customer not found"),
    TICKETS_SOLD_OUT("Tickets sold out"),
    INVALID_CREDENTIALS("Invalid username or password");
    private final String message;

    ErrorType(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
