package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.CustomerEntity;
import edu.iit.ticketingservice.dto.Customer;
import edu.iit.ticketingservice.repository.CustomerRepository;
import edu.iit.ticketingservice.repository.UserRepository;
import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
}
