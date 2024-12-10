package edu.iit.TicketingSimulation.simulation;

import edu.iit.TicketingSimulation.model.Customer;
import edu.iit.TicketingSimulation.model.Ticket;
import edu.iit.TicketingSimulation.util.FileLoggerUtil;
import edu.iit.TicketingSimulation.util.LoggingServer;

import java.util.LinkedList;
import java.util.Queue;

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
                LoggingServer.broadcastLog("Waiting to add ticket: " + ticket.getTicketId());
                wait();
            } catch (InterruptedException e) {
                LoggingServer.broadcastLog("Thread interrupted while waiting to add a ticket.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        String logMessage = "Ticket ID - " + ticket.getTicketId() + " added by Vendor ID - " + ticket.getVendorId();
        FileLoggerUtil.logToFile(logMessage);
        LoggingServer.broadcastLog(logMessage);
        notifyAll();
    }

    public synchronized Ticket getTicket(Customer customer) {
        while (tickets.isEmpty()) {
            try {
                LoggingServer.broadcastLog("Waiting for ticket retrieval by customer: " + customer.getCustomerId());
                wait();
            } catch (InterruptedException e) {
                LoggingServer.broadcastLog("Thread interrupted while waiting to get a ticket.");
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.poll();
        if (ticket != null) {
            String logMessage = "Customer ID - " + customer.getCustomerId()
                    + " retrieved Ticket ID - " + ticket.getTicketId();
            FileLoggerUtil.logToFile(logMessage);
            LoggingServer.broadcastLog(logMessage);
        } else {
            LoggingServer.broadcastLog("Warning: Customer " + customer.getCustomerId()
                    + " attempted to retrieve a ticket, but none were available.");
        }
        notifyAll();
        return ticket;
    }
}
