package com.oktaykcr.keycloakspringboot.common;

import java.util.Date;
import java.util.List;

public class ListResponse<T> {

    private List<T> data;
    private Long totalCount;
    private Date date;

    public static <T> ListResponse<T> response(List<T> data, long totalCount) {
        ListResponse<T> listResponse = new ListResponse<T>();
        listResponse.setData(data);
        listResponse.setTotalCount(totalCount);
        listResponse.setDate(new Date());
        return listResponse;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
