package com.oktaykcr.keycloakspringboot.service.base;

import com.oktaykcr.keycloakspringboot.common.ListResponse;
import com.oktaykcr.keycloakspringboot.entity.base.BaseEntity;

public abstract class BaseCrudService<T extends BaseEntity> {

    public abstract T save(T t);
    public abstract T update(T t);
    public abstract ListResponse<T> list(Integer pageNumber, Integer pageOffset);
    public abstract T findById(String id);
    public abstract void deleteById(String id);

}
