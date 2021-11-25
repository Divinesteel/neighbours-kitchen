package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.PurchaseSetDto;
import com.application.neighbourskitchen.dto.UserDetailsWrapperDto;
import com.application.neighbourskitchen.helper.UserActions;
import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.PurchaseFoodPortionsRepository;
import com.application.neighbourskitchen.repository.PurchaseRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    UserRepository userRepository;
    FoodRepository foodRepository;
    PurchaseRepository purchaseRepository;
    ModelMapper modelMapper;

    public PurchaseController(UserRepository userRepository, FoodRepository foodRepository, PurchaseRepository purchaseRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.purchaseRepository = purchaseRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/myOrders/{username}")
    @ResponseBody
    public PurchaseSetDto myOrders(@PathVariable String username){

        UserDetailsWrapperDto userWrapper = new UserDetailsWrapperDto();
        userWrapper.setUsername(username);

        UserActions<UserDetailsWrapperDto, PurchaseSetDto> userActions = new UserActions<>() {
            @Override
            public PurchaseSetDto actionOnVerified(User user) {
                Set<Purchase> purchaseSetObject = purchaseRepository.findByBuyer(userRepository.findByUsername(username).get());

                PurchaseSetDto purchaseSetDto = new PurchaseSetDto(purchaseSetObject);

                return purchaseSetDto;
            }
        };

        return userActions.actionOnVerified(userRepository.findByUsername(username).get());

    }

}
