package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.event.EventResponse;
import edu.iit.ticketingservice.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;


    // Create a new event - restricted to VENDOR role
    @PreAuthorize("hasRole('VENDOR')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        ApiResponse<Event> response = new ApiResponse<>(true, "Event created successfully", createdEvent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> getEventByEventId(@PathVariable("id") String eventId) {
        EventResponse event = eventService.getEventByEventId(eventId);
        if (event != null) {
            ApiResponse<EventResponse> response = new ApiResponse<>(true, "Event found", event);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<EventResponse> response = new ApiResponse<>(false, "Event not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}
