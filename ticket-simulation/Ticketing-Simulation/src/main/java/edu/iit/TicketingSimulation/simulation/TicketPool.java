package edu.iit.TicketingSimulation.simulation;

import java.util.LinkedList;
import java.util.Queue;

import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.Ticket;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.LogMessages;

public class TicketPool {
    private final int maxTicketCapacity;
    private final Queue<Ticket> tickets;

    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (this.tickets.size() >= maxTicketCapacity) {
            try {
                System.out.println("Waiting to add ticket: " + ticket.getTicketId());
                wait();
            } catch (InterruptedException e) {
                System.out.println(LogMessages.THREAD_INTERRUPTED.format("waiting to add a ticket"));
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        String logMessage = "Ticket ID - " + ticket.getTicketId() + " added by Vendor ID - " + ticket.getVendorId();
        FileLoggerUtil.logToFile(logMessage);
        notifyAll();
    }

    public synchronized Ticket getTicket(Customer customer) {
        while (tickets.isEmpty()) {
            try {
                System.out.println("Waiting for ticket retrieval by customer: " + customer.getCustomerId());
                wait();
            } catch (InterruptedException e) {
                System.out.println(LogMessages.THREAD_INTERRUPTED.format("waiting to get a ticket"));
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.poll();
        if (ticket != null) {
            FileLoggerUtil.logToFile("Customer ID - " + customer.getCustomerId()
                    + " retrieved Ticket ID - " + ticket.getTicketId());
        } else {
            System.out.println("Warning: Customer " + customer.getCustomerId()
                    + " attempted to retrieve a ticket, but none were available.");
        }
        notifyAll();
        return ticket;
    }
}
