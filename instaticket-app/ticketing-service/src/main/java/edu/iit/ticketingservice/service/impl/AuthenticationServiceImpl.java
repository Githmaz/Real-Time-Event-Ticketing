package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.UsersEntity;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.exception.BusinessException;
import edu.iit.ticketingservice.exception.ErrorType;
import edu.iit.ticketingservice.repository.UserRepository;
import edu.iit.ticketingservice.service.AuthenticationService;
import edu.iit.ticketingservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String login(Users user) {
        // Attempt to authenticate the user with the provided credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        // Check if authentication was successful
        if (authentication.isAuthenticated()) {
            UsersEntity authenticatedUser = userRepository.findByUsername(user.getUsername());
            return jwtUtil.generateToken(user.getUsername(),authenticatedUser.getUserId());
        } else {
            throw new BusinessException(ErrorType.INVALID_CREDENTIALS);
        }

    }


    @Override
    public void logout() {
    }
}
