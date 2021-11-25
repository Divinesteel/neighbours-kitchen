package com.application.neighbourskitchen.services;

import com.application.neighbourskitchen.dto.UserCredentialsDto;
import com.application.neighbourskitchen.model.UserAuth;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper userDetailsMapper;

    public DatabaseUserDetailsService(UserRepository userRepository, ModelMapper userDetailsMapper) {
        this.userRepository = userRepository;
        this.userDetailsMapper = userDetailsMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentialsDto user =
                userDetailsMapper.map(userRepository.findByUsername(username).get(),UserCredentialsDto.class);
        return new UserAuth(user);
    }
}
