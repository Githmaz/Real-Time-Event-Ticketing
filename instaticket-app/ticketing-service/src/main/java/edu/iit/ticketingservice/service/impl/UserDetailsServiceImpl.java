package edu.iit.ticketingservice.service.impl;

import edu.iit.ticketingservice.dao.UsersEntity;
import edu.iit.ticketingservice.dto.users.UserPrincipal;
import edu.iit.ticketingservice.dto.users.Users;
import edu.iit.ticketingservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UsersEntity usersEntity = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        return new UserPrincipal(modelMapper.map(usersEntity, Users.class)); // Wrapping UserEntity in UserPrincipal for Spring Security
    }
}

