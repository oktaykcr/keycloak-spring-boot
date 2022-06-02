package com.oktaykcr.keycloakspringboot.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionResponse {

    private Date timestamp;
    private HttpStatus httpStatus;
    private String message;

    public ApiExceptionResponse(HttpStatus httpStatus, String message) {
        this.timestamp = new Date();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
