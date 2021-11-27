package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Food not found")
public class FoodNotFoundException extends RuntimeException {
    public FoodNotFoundException(Long id) {
        super("Food " + id + " not found.");
    }
}