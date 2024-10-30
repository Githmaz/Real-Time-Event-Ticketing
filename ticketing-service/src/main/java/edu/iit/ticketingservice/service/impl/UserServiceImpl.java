package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.repository.UserRepository;
import edu.iit.ticketingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean checkUserEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return !userRepository.existsByUsername(username);
    }
}
