package edu.iit.ticketingservice.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_plan")
public class CustomerPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String planId;

    @Column(nullable = false)
    private String planName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    public CustomerPlanEntity(String planName, String description, double price) {
        this.planName = planName;
        this.description = description;
        this.price = price;

    }

    public CustomerPlanEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
    @PostPersist
    public void generatePlanId() {
        this.planId = "PL-"+id;
    }


}
