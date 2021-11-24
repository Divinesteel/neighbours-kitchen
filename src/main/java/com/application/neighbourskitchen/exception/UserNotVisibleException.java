package com.application.neighbourskitchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User not found or is not visible to public")
public class UserNotVisibleException extends RuntimeException {
    public UserNotVisibleException(String username) {
        super("Unauthorized Request, "+username+" may only see its own specific details.");
    }
}