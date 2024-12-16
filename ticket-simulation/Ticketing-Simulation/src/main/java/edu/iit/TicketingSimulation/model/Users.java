package edu.iit.TicketingSimulation.model;

import java.io.Serializable;

public class Users{
    protected  String userName;
    protected final TicketPool ticketPool; // Shared between Customers and Vendors

    public Users(String userName, TicketPool ticketPool) {
        this.userName = userName;
        this.ticketPool = ticketPool;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

}
