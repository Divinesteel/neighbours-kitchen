package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "The amount of portions provided are not available.")
public class FoodPortionsAreNotAvailableException extends RuntimeException {
    public FoodPortionsAreNotAvailableException(int portions) {
        super("The amount of portions "+portions+ " provided are not available.");
    }
}