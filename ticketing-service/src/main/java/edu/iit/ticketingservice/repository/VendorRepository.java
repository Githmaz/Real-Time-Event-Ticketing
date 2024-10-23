package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Integer> {
    // Query method to find a Vendor by email and password
    VendorEntity findByEmailAndPassword(String email, String password);

    // Query method to find a Vendor by username and password
    VendorEntity findByUsernameAndPassword(String username, String password);
}
