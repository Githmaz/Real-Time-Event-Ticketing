package edu.iit.ticketingservice.dto.dashboardData;


import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.event.EventResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Users;

import java.util.List;

public class CustomerDashboardData {
    private Customer customer;
    private List<EventResponse> eventList;

    public Users getUser() {
        return customer;
    }

    public CustomerDashboardData(Customer customer, List<EventResponse> eventlist) {
        this.customer = customer;
        this.eventList = eventlist;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public List<EventResponse> getEventList() {
        return eventList;
    }

    public void setEventlist(List<EventResponse> eventlist) {
        this.eventList = eventlist;
    }
}
