package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.UserCredentialsDto;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RegistrationResource {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationResource(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody UserCredentialsDto userCredentialsDto) {
        User user = User.builder()
                .enabled(true)
                .username(userCredentialsDto.getUsername())
                .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
                .address(userCredentialsDto.getAddress())
                .firstName(userCredentialsDto.getFirstName())
                .lastName(userCredentialsDto.getLastName())
                .phone(userCredentialsDto.getPhone())
                .isCook(userCredentialsDto.isCook())
                .enabled(true)
                .openToPublic(userCredentialsDto.isOpenToPublic())
                .build();
        userRepository.save(user);
    }
}
