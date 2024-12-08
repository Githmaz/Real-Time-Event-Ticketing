package edu.iit.TicketingSimulation.model;

import edu.iit.TicketingSimulation.simulation.TicketPool;

public class Customer implements Runnable {
    private static long idCounter = 0;
    private String customerId;
    private String customerName;
    private boolean isVIP;
    private int numberOfTicket;
    private int customerRetrievalRate;
    private TicketPool ticketPool;

    public Customer(String customerName, boolean isVIP, int customerRetrievalRate, TicketPool ticketPool,int numberOfTicket) {
        this.customerId = generateId();
        this.customerName = customerName;
        this.numberOfTicket = numberOfTicket;
        this.isVIP = isVIP;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketPool = ticketPool;
    }

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        Customer.idCounter = idCounter;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Synchronized method to generate unique customer ID
    private static synchronized String generateId() {
        return "C-" + (++idCounter);
    }

    @Override
    public void run() {
        System.out.println("Customer " + customerId + " has been created");
        for (int i = 0; i < numberOfTicket; i++) {
            Ticket ticket = ticketPool.getTicket(this);
            try {
                Thread.sleep(customerRetrievalRate*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
