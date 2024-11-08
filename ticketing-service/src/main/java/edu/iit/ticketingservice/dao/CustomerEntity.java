package edu.iit.ticketingservice.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "customer")
public class CustomerEntity extends UsersEntity {

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

}
