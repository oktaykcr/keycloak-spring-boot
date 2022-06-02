package com.oktaykcr.keycloakspringboot.common;

import com.oktaykcr.keycloakspringboot.entity.Employee;

import java.util.Date;

public class TestDataFactory {

    public static Employee createEmployee() {
        Employee employee = new Employee("test", "test adress", "test@test.com", 1L);
        employee.setId("id");
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());

        return employee;
    }
}
