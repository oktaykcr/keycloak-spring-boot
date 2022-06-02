package com.oktaykcr.keycloakspringboot.exception.base;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String message;

    public BusinessException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
