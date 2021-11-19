package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FoodListNotFoundException extends RuntimeException {
    public FoodListNotFoundException(String message) {
        super(message);
    }
}
