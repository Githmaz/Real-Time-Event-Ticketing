package edu.iit.ticketingservice.dao;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "customer")
public class CustomerEntity extends UsersEntity {


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<TicketEntity> purchasedTickets;  // List of tickets bought by the customer

    @ManyToOne
    @JoinColumn(name = "customer_plan_id")
    private CustomerPlanEntity customerPlan;

    public CustomerPlanEntity getCustomerPlan() {
        return customerPlan;
    }

    public void setCustomerPlan(CustomerPlanEntity customerPlan) {
        this.customerPlan = customerPlan;
    }

    public CustomerEntity() {
        super();
    }

    public CustomerEntity(Long id, String userId, String username, String password, String email, String name) {
        super(id, userId, username, password, email, name);
    }
    @PostPersist
    @Override
    public void generateUserId() {
        this.setUserId("C-" +this.getId());
    }

    public List<TicketEntity> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<TicketEntity> tickets) {
        this.purchasedTickets = tickets;
    }
}
