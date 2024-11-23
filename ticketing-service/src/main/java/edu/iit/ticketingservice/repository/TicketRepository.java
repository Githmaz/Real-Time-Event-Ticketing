package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
}
