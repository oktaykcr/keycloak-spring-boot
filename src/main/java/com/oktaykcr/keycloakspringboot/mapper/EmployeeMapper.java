package com.oktaykcr.keycloakspringboot.mapper;

import com.oktaykcr.keycloakspringboot.dto.EmployeeDto;
import com.oktaykcr.keycloakspringboot.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToDto(Employee employee);
    Employee dtoToEmployee(EmployeeDto employeeDTO);

}
