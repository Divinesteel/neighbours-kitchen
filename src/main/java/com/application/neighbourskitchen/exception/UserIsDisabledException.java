package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User is disabled")
public class UserIsDisabledException extends RuntimeException {
    public UserIsDisabledException(String username) {
        super("User "+username+" is disabled.");
    }
}