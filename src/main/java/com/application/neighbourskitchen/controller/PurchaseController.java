package com.application.neighbourskitchen.controller;

import com.application.neighbourskitchen.dto.*;
import com.application.neighbourskitchen.exception.*;
import com.application.neighbourskitchen.helper.UserActions;
import com.application.neighbourskitchen.model.Food;
import com.application.neighbourskitchen.model.Purchase;
import com.application.neighbourskitchen.model.PurchaseFoodPortions;
import com.application.neighbourskitchen.model.User;
import com.application.neighbourskitchen.repository.FoodRepository;
import com.application.neighbourskitchen.repository.PurchaseFoodPortionsRepository;
import com.application.neighbourskitchen.repository.PurchaseRepository;
import com.application.neighbourskitchen.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    PurchaseFoodPortionsRepository purchaseFoodPortionsRepository;
    ModelMapper modelMapper;

    public PurchaseController(UserRepository userRepository, FoodRepository foodRepository, PurchaseRepository purchaseRepository, PurchaseFoodPortionsRepository purchaseFoodPortionsRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseFoodPortionsRepository = purchaseFoodPortionsRepository;
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

    @PostMapping("/addFoodOnPurchase")
    @ResponseBody
    public PurchaseFoodPortionsDto addFoodOnPurchase(@RequestBody PurchaseFoodDto purchaseFoodDto, Authentication auth){

        UserActions<PurchaseFoodDto, PurchaseFoodPortionsDto> userActions = new UserActions<>() {
            @Override
            public PurchaseFoodPortionsDto actionOnVerified(User buyer) {
                Optional<Food> foodOpt = foodRepository.findById(purchaseFoodDto.getFood().getId());
                if(!foodOpt.isPresent()) throw new FoodNotFoundException(purchaseFoodDto.getFood().getId());
                Food food = foodOpt.get();

                Optional<Purchase> purchaseOpt = purchaseRepository.findById(purchaseFoodDto.getPurchase().getId());
                if(!purchaseOpt.isPresent()) throw new PurchaseNotFoundException(purchaseFoodDto.getPurchase().getId());
                Purchase purchase = purchaseOpt.get();

                if(!purchase.getBuyer().getUsername().equals(purchaseFoodDto.getUsername())) throw new UnauthorizedActionException(purchaseFoodDto.getUsername());
                if(food.getRealPortions()<purchaseFoodDto.getPortions()) throw new FoodPortionsAreNotAvailableException(purchaseFoodDto.getPortions());

                food.setRealPortions(food.getRealPortions()-purchaseFoodDto.getPortions());
                Food savedFood = foodRepository.save(food);

                purchase.setPrice(purchase.getPrice()+(savedFood.getPrice()*purchaseFoodDto.getPortions()));
                PurchaseFoodPortions purchaseFoodPortions = purchase.addFood(savedFood,purchaseFoodDto.getPortions());
                PurchaseFoodPortions savedPurchaseFoodPortions = purchaseFoodPortionsRepository.save(purchaseFoodPortions);

                return new PurchaseFoodPortionsDto(savedPurchaseFoodPortions);
            }
        };
        return userActions.verifyOwnAction(userRepository, modelMapper, auth, purchaseFoodDto, false);
    }
}
