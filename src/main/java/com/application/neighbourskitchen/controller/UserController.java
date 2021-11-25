package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.AllCooksListDto;
import com.application.neighbourskitchen.dto.UserDetailsWrapperDto;
import com.application.neighbourskitchen.exception.UserNotVisibleException;
import com.application.neighbourskitchen.helper.UserActions;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public UserDetailsWrapperDto getUserDetails(@PathVariable String username, Authentication auth) {
        UserDetailsWrapperDto userWrapper = new UserDetailsWrapperDto();
        userWrapper.setUsername(username);
        UserActions<UserDetailsWrapperDto, UserDetailsWrapperDto> userActions = new UserActions<>() {
            @Override
            public UserDetailsWrapperDto actionOnVerified(User user) {
                return modelMapper.map(user, UserDetailsWrapperDto.class);
            }

            @Override
            public UserDetailsWrapperDto actionOnUnverified(User user) {
                if (user.isOpenToPublic()) {
                    return modelMapper.map(user, UserDetailsWrapperDto.class);
                } else {
                    throw new UserNotVisibleException(user.getUsername());
                }
            }
        };
        return userActions.verifyOwnAction(userRepository, modelMapper, auth, userWrapper, false);
    }

    @GetMapping("/getAllCooksBasicDetails")
    @ResponseBody
    public AllCooksListDto getAllCookers() {
        AllCooksListDto allCooksListDto = new AllCooksListDto(userRepository.findByIsCook(true));

        return allCooksListDto;
    }

    @PutMapping("/setUserActiveStatus")
    @ResponseBody
    public UserDetailsWrapperDto setUserActiveStatus(@RequestBody UserDetailsWrapperDto userDto, Authentication auth) {

        UserActions<UserDetailsWrapperDto, UserDetailsWrapperDto> userActions = new UserActions<UserDetailsWrapperDto, UserDetailsWrapperDto>() {
            @Override
            public UserDetailsWrapperDto actionOnVerified(User user) {
                user.setUserStatus(userDto.isEnabled());
                userRepository.save(user);
                return modelMapper.map(user, UserDetailsWrapperDto.class);
            }
        };
        return userActions.verifyOwnAction(userRepository, modelMapper, auth, userDto, true);
    }
}
