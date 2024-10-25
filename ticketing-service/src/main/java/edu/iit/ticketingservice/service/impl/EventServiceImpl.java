package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.Event;
import edu.iit.ticketingservice.repository.EventRepository;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Event createEvent(Event event) {
        EventEntity eventEntity = convertToEntity(event);
        EventEntity savedEvent = eventRepository.save(eventEntity);
        return convertToDto(savedEvent);
    }

    @Override
    public Event getEventById(Long eventId) {
        Optional<EventEntity> eventEntity = eventRepository.findById(eventId);
        return eventEntity.map(this::convertToDto).orElse(null);
    }



    @Override
    public void deleteEvent(Long eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
        } else {
            throw new RuntimeException("Event not found");
        }
    }

    private EventEntity convertToEntity(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventName(event.getEventName());
        eventEntity.setEventDate(event.getEventDate());
        // Retrieve VendorEntity using vendorId
        Optional<VendorEntity> vendorEntityOpt = vendorRepository.findById((Long)event.getVendorId());
        if (vendorEntityOpt.isPresent()) {
            eventEntity.setVendor(vendorEntityOpt.get());
        } else {
            throw new RuntimeException("Vendor not found with ID: " + event.getVendorId());
        }
        return eventEntity;
    }

    private Event convertToDto(EventEntity eventEntity) {
        Event event = new Event();
        event.setEventId(eventEntity.getEventId());
        event.setEventName(eventEntity.getEventName());
        event.setEventDate(eventEntity.getEventDate());
        event.setVendorId(eventEntity.getVendor().getId());
        return event;
    }
}
