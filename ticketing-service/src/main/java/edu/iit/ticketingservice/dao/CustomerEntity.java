package edu.iit.ticketingservice.dao;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "customer")
public class CustomerEntity extends UsersEntity {


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<TicketEntity> tickets;  // List of tickets bought by the customer

    public CustomerEntity() {
        super();
    }

    public CustomerEntity(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
    @PostPersist
    @Override
    public void generateUserId() {
        this.setUserId("C-" +this.getId());
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
