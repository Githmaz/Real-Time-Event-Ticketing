package edu.iit.ticketingservice.repository;

import edu.iit.ticketingservice.dao.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    // Find an event by custom eventId (e.g., "E-1")
    Optional<EventEntity> findByEventId(String eventId);
}
