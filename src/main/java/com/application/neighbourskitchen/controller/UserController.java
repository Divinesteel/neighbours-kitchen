package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.UserCredentialsDto;
import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class UserController {

    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/{username}")
    @ResponseBody
    public UserDetailsDto getUserDetails(@PathVariable String username){
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isPresent()){
            return modelMapper.map(userOpt.get(),UserDetailsDto.class);
        }else{
            throw new UserNotFoundException("User "+username +" not found");
        }
    }
}