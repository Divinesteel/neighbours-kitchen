package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.AllCooksListDto;
import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserController(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getUserBasicDetails/{username}")
    @ResponseBody
    public UserDetailsDto getUserDetails(@PathVariable String username){
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isPresent()){
            return modelMapper.map(userOpt.get(),UserDetailsDto.class);
        }else{
            throw new UserNotFoundException(username);
        }
    }

    @GetMapping("/getAllCooksBasicDetails")
    public Set<UserDetailsDto> getAllCookers(){
        AllCooksListDto allCooksListDto = new AllCooksListDto(userRepository.findByIsCook(true));

        Set<UserDetailsDto> cooksDetailed = new HashSet<>();
        return cooksDetailed;
    }

}
