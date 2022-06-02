package com.oktaykcr.keycloakspringboot.service;

import com.oktaykcr.keycloakspringboot.common.ListResponse;
import com.oktaykcr.keycloakspringboot.common.NullAwareBeanUtilsBean;
import com.oktaykcr.keycloakspringboot.common.TestDataFactory;
import com.oktaykcr.keycloakspringboot.entity.Employee;
import com.oktaykcr.keycloakspringboot.exception.base.BusinessException;
import com.oktaykcr.keycloakspringboot.repository.EmployeeRepository;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private BeanUtilsBean beanUtil = new NullAwareBeanUtilsBean();

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void save_shouldCreateEmployee() {
        Employee employee = TestDataFactory.createEmployee();

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        Employee saved = employeeService.save(employee);

        Assertions.assertNotNull(saved.getId());
    }

    @Test
    public void update_shouldUpdateEmployee() {
        String expectedName = "updatedName";
        Employee employee = TestDataFactory.createEmployee();
        employee.setName(expectedName);

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);


        Mockito.when(employeeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(employee));

        Employee updated = employeeService.update(employee);

        Assertions.assertEquals(expectedName, updated.getName());
    }

    @Test
    public void update_employeeNotFound_shouldThrowNotFoundException() {
        Employee employee = TestDataFactory.createEmployee();
        String expectedMessage = String.format("Employee: %s not found", employee.getId());

        BusinessException businessException = Assertions.assertThrows(BusinessException.class, () -> {
            employeeService.update(employee);
        });

        Assertions.assertEquals(expectedMessage, businessException.getMessage());
    }

    @Test
    public void list_shouldFindAll() {
        Employee employee = TestDataFactory.createEmployee();
        List<Employee> mockedEmployeeList = Collections.singletonList(employee);
        ListResponse<Employee> mockedListResponse = ListResponse.response(mockedEmployeeList, mockedEmployeeList.size());

        Page<Employee> pageEmployee = new PageImpl<>(mockedEmployeeList);
        Mockito.doReturn(pageEmployee).when(employeeRepository).findAll(Mockito.any(Pageable.class));
        Mockito.doReturn((long) mockedEmployeeList.size()).when(employeeRepository).count();

        ListResponse<Employee> listResponse = employeeService.list(1, 1);


        Assertions.assertEquals(mockedListResponse.getTotalCount(), listResponse.getTotalCount());
        Assertions.assertEquals(mockedListResponse.getData().get(0), listResponse.getData().get(0));
    }

    @Test
    public void findById_shouldFindEmployeeById() {
        Employee employee = TestDataFactory.createEmployee();

        Mockito.when(employeeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(employee.getId());

        Assertions.assertEquals(employee.getId(), result.getId());
    }

    @Test
    public void deleteById_shouldDeleteEmployeeById() {
        Employee employee = TestDataFactory.createEmployee();

        Mockito.when(employeeRepository.findById(Mockito.anyString())).thenReturn(Optional.of(employee));
        Mockito.doNothing().when(employeeRepository).delete(Mockito.any(Employee.class));

        employeeService.deleteById(employee.getId());
    }
}
