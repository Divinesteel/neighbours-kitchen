package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.PurchaseDetailsDto;
import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.dto.UserDetailsWrapperDto;
import com.application.neighbourskitchen.helper.UserActions;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.PurchaseRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/addNewPurchase")
    @ResponseBody
    public PurchaseDetailsDto addNewPurchase(PurchaseDetailsDto purchaseDetailsDto){

        UserDetailsDto buyerDto = purchaseDetailsDto.getBuyer();
        UserDetailsWrapperDto buyerWrapperDto = new UserDetailsWrapperDto();

        UserActions<UserDetailsWrapperDto, PurchaseDetailsDto> buyerNewPurchaseAction = new UserActions<UserDetailsWrapperDto, PurchaseDetailsDto>() {
            @Override
            public PurchaseDetailsDto actionOnVerified(User buyer) {

                Purchase purchase = new Purchase(newPurchase.getPrice(), newPurchase.getSeller(), newPurchase.getBuyer(), new Date());
                PurchaseDetailsDto purchaseDetailsDto = new PurchaseDetailsDto(purchase);

            }
                return null;
            }
        }

        return null;
}
