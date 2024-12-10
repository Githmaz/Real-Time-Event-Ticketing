package edu.iit.TicketingSimulation.model;

import edu.iit.TicketingSimulation.simulation.TicketPool;

public class Vendor extends Users implements Runnable {
    private static int idCounter = 0;
    private final String vendorId;
    private int totalTickets;
    private int ticketReleaseRate;
    private double ticketPrice;

    public Vendor(String vendorName, int totalTickets, int ticketReleaseRate, TicketPool ticketPool, double ticketPrice) {
        super(vendorName, ticketPool);
        this.vendorId = generateId();
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPrice = ticketPrice;
    }

    private static synchronized String generateId() {
        return "V-" + (++idCounter);
    }

    public static synchronized void setIdCounter(int counter) {
        idCounter = counter;
    }

    public static synchronized void resetIdCounter() {
        idCounter = 0;
    }

    public String getVendorId() {
        return vendorId;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            ticketPool.addTicket(new Ticket(vendorId, ticketPrice));
            try {
                Thread.sleep(ticketReleaseRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }
    }
}
