package edu.iit.ticketingservice.config;

import edu.iit.ticketingservice.dao.CustomerPlanEntity;
import edu.iit.ticketingservice.repository.CustomerPlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing default data at application startup.
 *
 * Ensures essential data, such as default customer plans, is present in the database.
 * Uses a `CommandLineRunner` to insert predefined plans if the `customer_plan` table is empty.
 *
 * Default Plans:
 * - Standard: Basic access, free of charge.
 * - Pro: Priority access with discounts, $9.99.
 * - Premium: Comprehensive access with VIP features, $19.99.
 *
 * Dependencies:
 * - `CustomerPlanRepository` for database operations.
 * - `CustomerPlan` entity for representing subscription plans.
 */

@Configuration
public class DataInitializer {

    private final CustomerPlanRepository customerPlanRepository;

    public DataInitializer(CustomerPlanRepository customerPlanRepository) {
        this.customerPlanRepository = customerPlanRepository;
    }

    @Bean
    public CommandLineRunner loadDefaultPlans() {
        return args -> {
            if (customerPlanRepository.count() == 0) { // Fix logic here to check if table is empty
                savePlan("Standard", "Basic access to events and tickets", 0.00);
                savePlan("Pro", "Priority access with discounts", 9.99);
                savePlan("Premium", "All features with VIP access", 19.99);
            }
        };
    }

    private void savePlan(String name, String description, double price) {
        // Create and set planId before saving
        CustomerPlanEntity plan = new CustomerPlanEntity(name, description, price);
        plan.setPlanId(generatePlanId(customerPlanRepository.count() + 1));
        customerPlanRepository.save(plan);
    }

    private String generatePlanId(long count) {
        return "PL-" + count;
    }
}
