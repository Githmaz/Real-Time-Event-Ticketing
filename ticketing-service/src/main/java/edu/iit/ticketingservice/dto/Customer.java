package edu.iit.ticketingservice.dto;

import jakarta.persistence.Access;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
public class Customer extends User {

    public Customer() {
        super();
    }

    public Customer(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
//    private List<Ticket> purchasedTickets;
}
