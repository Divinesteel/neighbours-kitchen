package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatabaseUserDetailPasswordService
        implements UserDetailsPasswordService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;

    public DatabaseUserDetailPasswordService(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    // constructor ...

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        User userCredentials =
                userRepository.findByUsername(user.getUsername()).get();
        userCredentials.setPassword(newPassword);
        return userDetailsMapper.toUserDetails(userCredentials);
    }
}
