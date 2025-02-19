package edu.iit.ticketingservice.service.impl;


import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dao.CustomerPlanEntity;
import edu.iit.ticketingservice.dto.ticket.TicketResponse;
import edu.iit.ticketingservice.dto.users.Customer;
import edu.iit.ticketingservice.dto.users.CustomerPlan;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.CustomerPlanRepository;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.service.CustomerService;
import edu.iit.ticketingservice.service.TicketService;
import edu.iit.ticketingservice.service.UserService;
import edu.iit.ticketingservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerPlanRepository customerPlanRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TicketResponse> getBookedTicketsForCustomer() {
        // Get the authenticated customer
        Customer customer = getAuthenticatedCustomer();

        // Check if purchasedTickets is null and return an empty list if it is
        if (customer.getPurchasedTickets() == null) {
            return Collections.emptyList();
        }

        // Map the tickets to TicketResponse DTOs
        return customer.getPurchasedTickets().stream()
                .map(ticket -> modelMapper.map(ticket, TicketResponse.class))
                .collect(Collectors.toList());
    }
    @Override
    public Customer getAuthenticatedCustomer() {
        String customerId = userService.getAuthenticatedUserId();
        CustomerEntity customerEntity = customerRepository.findByUserId(customerId)
                .orElseThrow(() -> new BusinessException(ErrorType.CUSTOMER_NOT_FOUND));

        return modelMapper.map(customerEntity, Customer.class);
    }

    @Override
    public CustomerPlan getCustomerPlan() {
        // Get the authenticated customer
        Customer customer = getAuthenticatedCustomer();
        return customer.getCustomerPlan();
    }

    @Override
    public List<CustomerPlan> getAllCustomerPlans() {
        // Fetch all customer plans from the database
        return customerPlanRepository.findAll().stream()
                .map(planEntity -> modelMapper.map(planEntity, CustomerPlan.class))
                .collect(Collectors.toList());
    }

    @Override
    public Customer setCustomerPlan(CustomerPlan customerPlan) {
        // Get the authenticated customer
        CustomerEntity customerEntity = customerRepository.findByUserId(userService.getAuthenticatedUserId())
                .orElseThrow(() -> new BusinessException(ErrorType.CUSTOMER_NOT_FOUND));

        // Determine the new plan entity
        CustomerPlanEntity newPlanEntity = customerEntity.getCustomerPlan(); // Start with current plan

        // Update based on the provided plan details
        if (customerPlan != null) {
            // If plan name is provided, fetch by name
            if (customerPlan.getPlanName() != null) {
                newPlanEntity = customerPlanRepository.findByPlanName(customerPlan.getPlanName())
                        .orElseThrow(() -> new BusinessException(ErrorType.PLAN_NOT_FOUND));
            }
            // If plan ID is provided, fetch by ID
            if(customerPlan.getPlanId() != null) {
                newPlanEntity = customerPlanRepository.findByPlanId(customerPlan.getPlanId())
                        .orElseThrow(() -> new BusinessException(ErrorType.PLAN_NOT_FOUND));
            }
        }

        // If no new plan is provided or found, throw an exception
        if (newPlanEntity == null) {
            throw new BusinessException("No valid plan details provided");
        }

        // Update the customer's plan
        customerEntity.setCustomerPlan(newPlanEntity);
        customerRepository.save(customerEntity);

        // Return the updated customer as a DTO
        return modelMapper.map(customerEntity, Customer.class);
    }

}
