package edu.iit.TicketingSimulation.model;

import edu.iit.TicketingSimulation.simulation.TicketPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends Users implements Runnable {
    private static long idCounter = 0;
    private final String customerId;
    private boolean isVIP;
    private int numberOfTickets;
    private int customerRetrievalRate;
    private final List<Ticket> purchasedTickets = Collections.synchronizedList(new ArrayList<>());

    public Customer(String customerName, boolean isVIP, int customerRetrievalRate, TicketPool ticketPool, int numberOfTickets) {
        super(customerName, ticketPool);
        this.customerId = generateId();
        this.isVIP = isVIP;
        this.customerRetrievalRate = customerRetrievalRate;
        this.numberOfTickets = numberOfTickets;
    }

    private static synchronized String generateId() {
        return "C-" + (++idCounter);
    }

    public static synchronized void setIdCounter(long counter) {
        idCounter = counter;
    }

    public static synchronized void resetIdCounter() {
        idCounter = 0;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public boolean isVIP() {
        return this.isVIP;
    }

    public void setVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    public int getNumberOfTickets() {
        return this.numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = ticketPool.getTicket(this);
            if (ticket != null) {
                 purchasedTickets.add(ticket);
            }
            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }
    }
}
