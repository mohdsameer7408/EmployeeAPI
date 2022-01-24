package com.example.employee_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomIdNotFoundException extends RuntimeException {
    public CustomIdNotFoundException(String message) {
        super(message);
    }

    public CustomIdNotFoundException() {
    }
}
