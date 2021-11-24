package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Unauthorized action")
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String username) {
        super("User " + username + " is not authorized to access this resource.");
    }
}