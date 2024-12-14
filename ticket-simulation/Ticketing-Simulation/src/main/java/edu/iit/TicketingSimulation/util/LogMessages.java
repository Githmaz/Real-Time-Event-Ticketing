package edu.iit.TicketingSimulation.util;

public enum LogMessages {
    TICKET_ADDED("Ticket ID - %s added by Vendor ID - %s"),
    TICKET_SOLD("Ticket ID - %s sold to Customer ID - %s"),
    POOL_INITIALIZED("TicketPool has been successfully initialized with max capacity: %s"),
    POOL_ALREADY_INITIALIZED("TicketPool is already initialized. Max capacity: %s"),
    THREAD_INTERRUPTED("Thread interrupted while %s");
    private final String message;

    LogMessages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public String format(Object... args) {
        return String.format(message, args);
    }
}
