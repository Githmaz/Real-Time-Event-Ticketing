package edu.iit.ticketingservice.dto.dashboardData;


import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Users;

import java.util.List;

public class CustomerDashboardData {
    private Customer customer;
    private List<Event> eventList;

    public Users getUser() {
        return customer;
    }

    public CustomerDashboardData(Customer customer, List<Event> eventlist) {
        this.customer = customer;
        this.eventList = eventlist;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventlist(List<Event> eventlist) {
        this.eventList = eventlist;
    }
}
