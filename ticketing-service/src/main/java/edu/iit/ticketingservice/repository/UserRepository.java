package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.UsersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UsersEntity, Long> {
    UsersEntity findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<UsersEntity> findByEmailOrUsername(String email, String username);
}
