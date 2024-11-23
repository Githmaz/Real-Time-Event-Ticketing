package edu.iit.ticketingservice.dto.users;


import edu.iit.ticketingservice.dao.TicketEntity;

import java.util.List;

public class Customer extends Users {

    private List<TicketEntity> purchasedTickets;

    public Customer() {
        super();
    }

    public Customer(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }

    public List<TicketEntity> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<TicketEntity> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
