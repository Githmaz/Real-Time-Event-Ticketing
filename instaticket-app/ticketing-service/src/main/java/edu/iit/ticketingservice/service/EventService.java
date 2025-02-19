package edu.iit.ticketingservice.service;

import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.event.EventResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    Event createEvent(Event event);
    EventResponse getEventByEventId(String eventId);
    void deleteEvent(Long eventId);
    List<Event> getAllEvents();
}
