package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUserId(String userid);
    CustomerEntity findByEmail(String email);
    CustomerEntity findByUsername(String username);
}
