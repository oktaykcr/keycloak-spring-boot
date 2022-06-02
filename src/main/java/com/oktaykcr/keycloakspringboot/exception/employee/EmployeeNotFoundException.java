package com.oktaykcr.keycloakspringboot.exception.employee;

import com.oktaykcr.keycloakspringboot.exception.base.BusinessException;
import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends BusinessException {


    public EmployeeNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, String.format("Employee: %s not found", id));
    }
}
