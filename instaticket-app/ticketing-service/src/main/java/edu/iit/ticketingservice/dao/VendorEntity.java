package edu.iit.ticketingservice.dao;

import jakarta.persistence.*;


@Entity
@Table(name = "vendor")
public class VendorEntity  extends UsersEntity {

    public VendorEntity(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }

    public VendorEntity() {
        super();
    }
    @PostPersist
    @Override
    public void generateUserId() {
        this.setUserId("V-" + this.getId());
    }

}
