package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
