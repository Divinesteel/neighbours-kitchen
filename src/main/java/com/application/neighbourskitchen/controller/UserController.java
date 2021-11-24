package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.AllCooksListDto;
import com.application.neighbourskitchen.dto.UserCredentialsDto;
import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.dto.UserStatusDto;
import com.application.neighbourskitchen.exception.UnauthorizedActionException;
import com.application.neighbourskitchen.exception.UserIsDisabledException;
import com.application.neighbourskitchen.exception.UserNotVisibleException;
import com.application.neighbourskitchen.exception.UserNotFoundException;
import com.application.neighbourskitchen.model.UserAuth;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public UserDetailsDto getUserDetails(@PathVariable String username, Authentication auth){
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            if(user.isEnabled()){
                UserAuth userPrincipal = (UserAuth) auth.getPrincipal();
                if(userPrincipal.getUsername().equals(username)){
                    return modelMapper.map(userOpt.get(),UserDetailsDto.class);
                }else{
                    if(user.isOpenToPublic()){
                        return modelMapper.map(userOpt.get(),UserDetailsDto.class);
                    }else{
                        throw new UserNotVisibleException(username);
                    }
                }
            }else{
                throw new UserIsDisabledException(username);
            }
        }else{
            throw new UserNotFoundException(username);
        }
    }

    @GetMapping("/getAllCooksBasicDetails")
    @ResponseBody
    public AllCooksListDto getAllCookers(){
        AllCooksListDto allCooksListDto = new AllCooksListDto(userRepository.findByIsCook(true));

        return allCooksListDto;
    }

    @PutMapping("/setUserActiveStatus")
    @ResponseBody
    public UserStatusDto setUserActiveStatus(@RequestBody UserStatusDto userDto, Authentication auth){
        Optional<User> userOpt = userRepository.findById(userDto.getUsername());
        if (userOpt.isPresent()){
            UserAuth userPrincipal = (UserAuth) auth.getPrincipal();
            if(userPrincipal.getUsername().equals(userDto.getUsername())){
                userOpt.get().setUserStatus(userDto.isEnabled());
                userRepository.save(userOpt.get());
                return modelMapper.map(userOpt.get(),UserStatusDto.class);
            }else{
                throw new UnauthorizedActionException(userDto.getUsername());
            }
        }else{
            throw new UserNotFoundException(userDto.getUsername());
        }
    }

}
