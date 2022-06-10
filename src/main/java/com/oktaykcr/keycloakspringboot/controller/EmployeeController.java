package com.oktaykcr.keycloakspringboot.controller;

import com.oktaykcr.keycloakspringboot.common.ApiResponse;
import com.oktaykcr.keycloakspringboot.common.ListResponse;
import com.oktaykcr.keycloakspringboot.dto.EmployeeDto;
import com.oktaykcr.keycloakspringboot.entity.Employee;
import com.oktaykcr.keycloakspringboot.mapper.EmployeeMapper;
import com.oktaykcr.keycloakspringboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    private ApiResponse<EmployeeDto> create(@RequestBody @Valid EmployeeDto employeeDTO) {
        Employee employeeRequest = employeeMapper.dtoToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.save(employeeRequest);
        EmployeeDto responseDto = employeeMapper.employeeToDto(savedEmployee);
        return ApiResponse.response(responseDto);
    }

    @PutMapping
    private ApiResponse<EmployeeDto> update(@RequestBody @Valid EmployeeDto employeeDTO) {
        Employee employeeRequest = employeeMapper.dtoToEmployee(employeeDTO);
        Employee updatedEmployee = employeeService.update(employeeRequest);
        EmployeeDto responseDto = employeeMapper.employeeToDto(updatedEmployee);
        return ApiResponse.response(responseDto);
    }

    @GetMapping("{id}")
    private ApiResponse<EmployeeDto> findById(@PathVariable @NotNull String id) {
        Employee byId = employeeService.findById(id);

        return ApiResponse.response(employeeMapper.employeeToDto(byId));
    }

    @GetMapping()
    private ListResponse<EmployeeDto> list(@RequestParam @Min(0) Integer pageNumber,
                                               @RequestParam @Min(1) Integer pageOffset) {
        ListResponse<Employee> employeeListResponse = employeeService.list(pageNumber, pageOffset);
        List<EmployeeDto> collect = employeeListResponse.getData().stream().map(employeeMapper::employeeToDto).toList();

        return ListResponse.response(collect, employeeListResponse.getTotalCount());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NotNull String id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
