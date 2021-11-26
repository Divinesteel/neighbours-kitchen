package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "User cannot buy from self.")
public class CannotBuyFromSelf extends RuntimeException {
    public CannotBuyFromSelf(String username) {
        super("User " + username + " cannot buy from self.");
    }
}