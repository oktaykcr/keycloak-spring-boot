package com.oktaykcr.keycloakspringboot.exception;

import com.oktaykcr.keycloakspringboot.exception.base.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class DefaultExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        String msg = sb.toString();

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST, msg);
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ApiExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotReadablePropertyException.class)
    public final ResponseEntity<ApiExceptionResponse> handleNotReadablePropertyException(NotReadablePropertyException ex) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiExceptionResponse> handleException(Exception ex) {
        logger.error("INTERNAL ERROR", ex);
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ApiExceptionResponse> handleBusinessException(BusinessException ex) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(ex.getHttpStatus(), ex.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, ex.getHttpStatus());
    }

}
