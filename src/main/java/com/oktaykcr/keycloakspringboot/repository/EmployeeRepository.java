package com.oktaykcr.keycloakspringboot.repository;

import com.oktaykcr.keycloakspringboot.entity.Employee;
import com.oktaykcr.keycloakspringboot.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee> {
}
