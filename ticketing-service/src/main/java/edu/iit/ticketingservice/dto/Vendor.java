package edu.iit.ticketingservice.dto;



public class Vendor extends Users {

    public Vendor() {
        super();
    }
    public Vendor(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
    //    private List<Ticket> ticketsForSale;
}
