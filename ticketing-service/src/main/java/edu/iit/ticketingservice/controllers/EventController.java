package edu.iit.ticketingservice.controllers;

import edu.iit.ticketingservice.dto.ApiResponse;
import edu.iit.ticketingservice.dto.Event;
import edu.iit.ticketingservice.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create a new event
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        ApiResponse<Event> response = new ApiResponse<>(true, "Event created successfully", createdEvent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            ApiResponse<Event> response = new ApiResponse<>(true, "Event found", event);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Event> response = new ApiResponse<>(false, "Event not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an event by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            ApiResponse<Void> response = new ApiResponse<>(true, "Event deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            ApiResponse<Void> response = new ApiResponse<>(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

//    // Retrieve all events
//    @GetMapping("/all")
//    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents() {
//        List<Event> events = eventService.getAllEvents();
//        ApiResponse<List<Event>> response = new ApiResponse<>(true, "Events retrieved successfully", events);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
