package edu.iit.ticketingservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Vendor extends User {

    public Vendor() {
        super();
    }
    public Vendor(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
    //    private List<Ticket> ticketsForSale;
}
