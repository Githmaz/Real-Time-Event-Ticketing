package edu.iit.TicketingSimulation.model;

import edu.iit.TicketingSimulation.simulation.TicketPool;

public class Vendor implements Runnable {
    private static int idCounter = 0 ;
    private String vendorId;
    private String vendorName;
    private int totalTickets;
    private int ticketReleaseRate;
    private TicketPool ticketPool;
    private double ticketPrice;

    public Vendor(String vendorName, int totalTickets, int ticketReleaseRate, TicketPool ticketPool, double ticketPrice) {
        this.vendorId = generateId();
        this.vendorName = vendorName;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.ticketPrice = ticketPrice;
    }

    // Synchronized method to generate unique vendor ID
    private static synchronized String generateId() {
        return "V-" + (++idCounter);
    }
    @Override
    public void run(){
        System.out.println("Vendor " + vendorId + " has been created");
        for (int i = 1; i <=totalTickets; i++) {
            Ticket ticket = new Ticket(this.vendorId,this.ticketPrice);
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate*1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
