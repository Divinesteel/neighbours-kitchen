package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.PurchaseDetailsDto;
import com.application.neighbourskitchen.dto.UserDetailsDto;
import com.application.neighbourskitchen.dto.UserDetailsWrapperDto;
import com.application.neighbourskitchen.exception.CannotBuyFromSelf;
import com.application.neighbourskitchen.exception.SellerIsNotACook;
import com.application.neighbourskitchen.exception.UserNotFoundException;
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
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public PurchaseDetailsDto addNewPurchase(@RequestBody PurchaseDetailsDto purchaseDetailsDto, Authentication auth){

        UserDetailsDto buyerDto = purchaseDetailsDto.getBuyer();
        UserDetailsWrapperDto buyerWrapperDto = new UserDetailsWrapperDto(buyerDto);

        UserActions<UserDetailsWrapperDto, PurchaseDetailsDto> buyerNewPurchaseAction = new UserActions<UserDetailsWrapperDto, PurchaseDetailsDto>() {
            @Override
            public PurchaseDetailsDto actionOnVerified(User buyer) {
                String sellerUsername = purchaseDetailsDto.getSeller().getUsername();
                String buyerUsername = buyer.getUsername();
                if(sellerUsername.equals(buyerUsername))throw new CannotBuyFromSelf(buyer.getUsername());
                Optional<User> sellerOpt = userRepository.findByUsername(sellerUsername);
                if(!sellerOpt.isPresent())throw new UserNotFoundException(sellerUsername);
                if(!sellerOpt.get().isCook())throw new SellerIsNotACook(sellerUsername);

                Purchase purchase = Purchase.builder()
                        .price(0.0)
                        .seller(sellerOpt.get())
                        .buyer(buyer)
                        .date(new Date())
                        .isCompleted(false)
                        .build();

                Purchase savedPurchase = purchaseRepository.save(purchase);

                PurchaseDetailsDto purchaseDetailsDto = new PurchaseDetailsDto(savedPurchase);
                return purchaseDetailsDto;
            }
        };
        return buyerNewPurchaseAction.verifyOwnAction(userRepository, modelMapper, auth, buyerWrapperDto, false);
    }
}
