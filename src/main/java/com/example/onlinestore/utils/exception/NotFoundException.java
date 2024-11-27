package com.example.onlinestore.utils.exception;

import org.springframework.http.HttpStatus;

import com.example.onlinestore.utils.ApiException;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
