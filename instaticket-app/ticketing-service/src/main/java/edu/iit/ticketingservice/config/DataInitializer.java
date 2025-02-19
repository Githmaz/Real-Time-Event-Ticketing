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
 * - Pro: Priority access with discounts, $9.99 (10% discount).
 * - Premium: Comprehensive access with VIP features, $19.99 (15% discount).
 *
 * Dependencies:
 * - `CustomerPlanRepository` for database operations.
 * - `CustomerPlanEntity` entity for representing subscription plans.
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
            if (customerPlanRepository.count() == 0) { // Check if table is empty
                savePlan("Standard", "Basic access to events and tickets", 0.00, 0.0);
                savePlan("Pro", "Priority access with discounts", 9.99, 5.0);
                savePlan("Premium", "All features with VIP access", 19.99, 10.0);
            }
        };
    }

    private void savePlan(String name, String description, double price, double discountPercentage) {
        CustomerPlanEntity plan = new CustomerPlanEntity(name, description, price, discountPercentage);
        plan.setPlanId(generatePlanId(customerPlanRepository.count() + 1));
        customerPlanRepository.save(plan);
    }

    private String generatePlanId(long count) {
        return "PL-" + count;
    }
}
