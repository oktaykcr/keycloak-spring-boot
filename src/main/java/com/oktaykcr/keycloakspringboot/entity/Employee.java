package com.oktaykcr.keycloakspringboot.entity;

import com.oktaykcr.keycloakspringboot.entity.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Employee extends BaseEntity {

    private String name;
    private String address;
    private String email;
    private Long salary;

    public Employee() {
    }

    public Employee(String name, String address, String email, Long salary) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
