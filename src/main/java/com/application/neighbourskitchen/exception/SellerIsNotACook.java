package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "Seller is not a cook")
public class SellerIsNotACook extends RuntimeException {
    public SellerIsNotACook(String username) {
        super("User " + username + " is not a cook.");
    }
}