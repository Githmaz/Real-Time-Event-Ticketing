package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long> {

}
