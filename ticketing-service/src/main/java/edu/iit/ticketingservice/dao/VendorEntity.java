package edu.iit.ticketingservice.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "vendor")
public class VendorEntity  extends UserEntity{

    public VendorEntity(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }

    public VendorEntity() {
        super();
    }
    @PostPersist
    @Override
    public void generateUserId() {
        this.setUserId("V-" + getUserId());
    }
    //    private List<Tickts> ticketsForSale;
}
