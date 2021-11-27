package com.application.neighbourskitchen.exception;

import com.application.neighbourskitchen.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "Seller User in purchase does not match with Cook User in food")
public class PurchaseSellerFoodCookAreDifferent extends RuntimeException {
    public PurchaseSellerFoodCookAreDifferent(User purchaseSeller , User foodCook) {
        super("Seller User "+purchaseSeller.getUsername()+" in purchase does not match with Cook User "+foodCook.getUsername()+" in food");
    }
}