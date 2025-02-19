package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dao.TicketPackageEntity;
import edu.iit.ticketingservice.dao.VendorEntity;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackage;
import edu.iit.ticketingservice.dto.ticketPackage.TicketPackageRequest;
import edu.iit.ticketingservice.dto.users.Vendor;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.EventRepository;
import edu.iit.ticketingservice.repository.TicketPackageRepository;
import edu.iit.ticketingservice.repository.VendorRepository;
import edu.iit.ticketingservice.service.TicketPackageService;
import edu.iit.ticketingservice.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketPackageServiceImpl implements TicketPackageService {

    @Autowired
    private TicketPackageRepository ticketPackageRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VendorService vendorService;
    @Override
    public TicketPackage createTicketPackage(TicketPackageRequest request) {

        // Get authenticated vendor using VendorService
        Vendor authenticatedVendor = vendorService.getAuthenticatedVendor();

        // Fetch Event using custom eventId
        EventEntity event = eventRepository.findByEventId(request.getEventId())
                .orElseThrow(() -> new BusinessException(ErrorType.EVENT_NOT_FOUND));

        if (!event.getVendor().getUserId().equals(authenticatedVendor.getUserId())) {
            throw new BusinessException(ErrorType.UNAUTHORIZED_ACCESS);
        }

        // Create Ticket Package Entity
        TicketPackageEntity ticketPackageEntity = new TicketPackageEntity();
        ticketPackageEntity.setPackageType(request.getPackageType());
        ticketPackageEntity.setPrice(request.getPrice());
        ticketPackageEntity.setEvent(event);
        ticketPackageEntity.setAvailableTickets(request.getTicketCount());
        ticketPackageEntity.setTicketCount(request.getTicketCount());
        ticketPackageEntity.generatePackageId();

        // Save Ticket Package
        TicketPackageEntity savedPackage = ticketPackageRepository.save(ticketPackageEntity);
        return convertToDto(savedPackage);
    }

    private TicketPackage convertToDto(TicketPackageEntity entity) {
        TicketPackage dto = new TicketPackage();
        dto.setPackageId(entity.getPackageId());
        dto.setPackageType(entity.getPackageType());
        dto.setPrice(entity.getPrice());
        dto.setEvent(entity.getEvent());
        dto.setTicketCount(entity.getTicketCount());  // Include ticket count
        return dto;
    }

}
