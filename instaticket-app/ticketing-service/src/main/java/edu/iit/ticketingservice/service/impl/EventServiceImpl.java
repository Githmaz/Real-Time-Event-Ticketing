package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.users.Vendor;
import edu.iit.ticketingservice.repository.EventRepository;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.EventService;
import edu.iit.ticketingservice.service.VendorService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Event createEvent(Event event) {
        Vendor vendor = vendorService.getAuthenticatedVendor();
        event.setVendorId(vendor.getUserId());
        EventEntity eventEntity = convertToEntity(event);
        eventEntity.generateEventId();
        EventEntity savedEvent = eventRepository.save(eventEntity);
        return convertToDto(savedEvent);
    }

    @Override
    public Event getEventByEventId(String eventId) {
        Optional<EventEntity> eventEntity = eventRepository.findByEventId(eventId);
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

    @Override
    public List<Event> getAllEvents() {
        List<EventEntity> eventEntities = eventRepository.findAll();
        return eventEntities.stream()
                .map(entity -> modelMapper.map(entity, Event.class))
                .collect(Collectors.toList());
    }

    private EventEntity convertToEntity(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(event.getEventId());
        eventEntity.setEventName(event.getEventName());
        eventEntity.setEventDateTime(event.getEventDateTime());
        eventEntity.setEventCreatedDate(event.getEventCreatedDate());
        eventEntity.setLocation(event.getLocation());

        // Retrieve VendorEntity using vendorId
        Optional<VendorEntity> vendorEntityOpt = (vendorRepository.findByUserId(event.getVendorId()));
        if (vendorEntityOpt.isPresent()) {
            eventEntity.setVendor(vendorEntityOpt.get());
        } else {
            throw new RuntimeException("Vendor not found with ID: " + event.getVendorId());
        }
        return eventEntity;
    }

    private Event convertToDto(EventEntity eventEntity) {
        Event event = new Event();
        event.setId(eventEntity.getId());
        event.setEventId(eventEntity.getEventId());
        event.setEventName(eventEntity.getEventName());
        event.setEventCreatedDate(eventEntity.getEventCreatedDate());
        event.setEventDateTime(eventEntity.getEventDateTime());
        event.setVendorId(eventEntity.getVendor().getUserId());
        event.setLocation(eventEntity.getLocation());
        event.setTicketPackages(eventEntity.getTicketPackages());
        return event;
    }
}
