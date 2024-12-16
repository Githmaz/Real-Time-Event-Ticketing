package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.EventEntity;
import edu.iit.ticketingservice.dto.dashboardData.CustomerDashboardData;
import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.DashboardService;
import edu.iit.ticketingservice.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            customerData =customerService.getAuthenticatedCustomer();
        } else {
            // For unauthenticated users, return a default guest user
            customerData = new Customer();
            customerData.setName("Guest");
        }

        List<Event> upcomingEvents = eventService.getAllEvents();
        return new CustomerDashboardData(customerData,upcomingEvents); // Replace null with userData when needed
    }
}
