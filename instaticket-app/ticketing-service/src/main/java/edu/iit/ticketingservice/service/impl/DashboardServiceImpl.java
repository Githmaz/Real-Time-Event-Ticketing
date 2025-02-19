package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dto.dashboardData.CustomerDashboardData;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.event.EventResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.DashboardService;
import edu.iit.ticketingservice.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public CustomerDashboardData getCustomerDashboardData() {
        Customer customerData;
        // Check for authentication
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            customerData = customerService.getAuthenticatedCustomer();
        } else {
            // For unauthenticated users, return a default guest user
            customerData = new Customer();
            customerData.setName("Guest");
        }

        // Fetch events and map them to EventResponse inside the method itself
        List<EventResponse> upcomingEvents = eventService.getAllEvents().stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());

        return new CustomerDashboardData(customerData, upcomingEvents);
    }


    /**
     * Converts a single Event object to an EventResponse object.
     */
    private EventResponse mapToEventResponse(Event event) {
        EventResponse response = new EventResponse();
        response.setId(event.getId());
        response.setEventId(event.getEventId());
        response.setEventName(event.getEventName());
        response.setEventDateTime(event.getEventDateTime());
        response.setEventCreatedDate(event.getEventCreatedDate());
        response.setLocation(event.getLocation());
        response.setVendorId(event.getVendorId());
        response.setVendorName(event.getVendor().getName());
        response.setTicketPackages(event.getTicketPackages());
        return response;
    }
}
