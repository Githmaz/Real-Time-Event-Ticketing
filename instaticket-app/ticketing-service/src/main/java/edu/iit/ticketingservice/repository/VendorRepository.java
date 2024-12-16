package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Long> {

    VendorEntity findByEmail(String email);

    VendorEntity findByUsername(String username);

    Optional<VendorEntity> findByUserId(String userId);
}
