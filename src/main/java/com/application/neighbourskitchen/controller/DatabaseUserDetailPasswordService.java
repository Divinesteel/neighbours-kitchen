package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatabaseUserDetailPasswordService
        implements UserDetailsPasswordService {

    private final UserRepository userRepository;
    private final ModelMapper userDetailsMapper;

    public DatabaseUserDetailPasswordService(UserRepository userRepository, ModelMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User userCredentials =
                userRepository.findByUsername(user.getUsername()).get();
        userCredentials.setPassword(newPassword);
        return userDetailsMapper.map(userCredentials,UserDetails.class);
    }
}
