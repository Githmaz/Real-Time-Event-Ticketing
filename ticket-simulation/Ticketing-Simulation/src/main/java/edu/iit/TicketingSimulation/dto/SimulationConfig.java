package edu.iit.TicketingSimulation.dto;

public class SimulationConfig {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customRetrievalRate;
    private int maxTicketCapacity;

    public SimulationConfig(int totalTickets, int ticketReleaseRate, int customRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customRetrievalRate = customRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getters and setters
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

    public int getCustomRetrievalRate() {
        return customRetrievalRate;
    }

    public void setCustomRetrievalRate(int customRetrievalRate) {
        this.customRetrievalRate = customRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
