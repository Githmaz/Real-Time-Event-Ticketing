package edu.iit.ticketingservice.dto.dashboardData;


import edu.iit.ticketingservice.dto.event.Event;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.Users;

import java.util.List;

public class CustomerDashboardData {
    private Customer customer;
    private List<Event> eventlist;

    public Users getUser() {
        return customer;
    }

    public CustomerDashboardData(Customer customer, List<Event> eventlist) {
        this.customer = customer;
        this.eventlist = eventlist;
    }

    public void setUser(Customer customer) {
        this.customer = customer;
    }

    public List<Event> getEventlist() {
        return eventlist;
    }

    public void setEventlist(List<Event> eventlist) {
        this.eventlist = eventlist;
    }
}
