package com.oktaykcr.keycloakspringboot.common;

import java.util.Date;

public class ApiResponse<T> {

    private T data;
    private Date date;

    public static <T> ApiResponse<T> response(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setDate(new Date());
        return apiResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
