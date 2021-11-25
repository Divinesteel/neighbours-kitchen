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
import org.springframework.security.core.Authentication;
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



}
