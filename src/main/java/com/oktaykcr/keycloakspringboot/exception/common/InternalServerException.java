package com.oktaykcr.keycloakspringboot.exception.common;

import com.oktaykcr.keycloakspringboot.exception.base.BusinessException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends BusinessException {

    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Internal Server Exception: %s", message));
    }
}
