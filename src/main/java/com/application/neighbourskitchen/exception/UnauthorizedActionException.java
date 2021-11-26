package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Unauthorized action")
public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String username) {
        super("User " + username + " is not authorized to access this resource.");
    }
    public UnauthorizedActionException() {
        super("You need to log in to view the resource.");
    }
}