package com.example.onlinestore.utils.exception;

import org.springframework.http.HttpStatus;

import com.example.onlinestore.utils.ApiException;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }
}
