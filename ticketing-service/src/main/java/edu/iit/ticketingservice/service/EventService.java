package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    Event createEvent(Event event);
    Event getEventByEventId(String eventId);
    void deleteEvent(Long eventId);
    List<Event> getAllEvents();
}
