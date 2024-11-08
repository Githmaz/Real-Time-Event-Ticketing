package edu.iit.ticketingservice.dto;



public class Customer extends Users {

    public Customer() {
        super();
    }

    public Customer(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
//    private List<Ticket> purchasedTickets;
}
