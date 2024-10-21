package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    // Query method to find a customer by email and password
    CustomerEntity findByEmailAndPassword(String email, String password);

    // Query method to find a customer by username and password
    CustomerEntity findByUsernameAndPassword(String username, String password);
}
