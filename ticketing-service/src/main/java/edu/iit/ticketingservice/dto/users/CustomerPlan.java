package edu.iit.ticketingservice.dto.users;


public class CustomerPlan {

    private String planId;
    private String planName;
    private String description;
    private double price;

    public CustomerPlan() {
    }

    public CustomerPlan(String planId, String planName, String description, double price) {
        this.planId = planId;
        this.planName = planName;
        this.description = description;
        this.price = price;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanId() {
        return planId;
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

    public CustomerPlan( String planName, String description, double price) {
        this.planName = planName;
        this.description = description;
        this.price = price;
    }

}

