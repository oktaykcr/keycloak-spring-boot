package com.oktaykcr.keycloakspringboot.service;

import com.oktaykcr.keycloakspringboot.common.ListResponse;
import com.oktaykcr.keycloakspringboot.common.TestDataFactory;
import com.oktaykcr.keycloakspringboot.entity.Employee;
import com.oktaykcr.keycloakspringboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@SpringBootTest
@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql("createEmployee.sql")
public class EmployeeServiceIT {

    @Container
    public static PostgreSQLContainer<?> pgsql = new PostgreSQLContainer<>("postgres:latest");

    @DynamicPropertySource
    static void configureTestContainersProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", pgsql::getJdbcUrl);
        registry.add("spring.datasource.username", pgsql::getUsername);
        registry.add("spring.datasource.password", pgsql::getPassword);
    }

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void save() {
        Employee employee = TestDataFactory.createEmployee();
        employee.setId(null);

        Employee saved = employeeService.save(employee);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(employee.getName(), saved.getName());
        Assertions.assertEquals(employee.getAddress(), saved.getAddress());
        Assertions.assertEquals(employee.getEmail(), saved.getEmail());
        Assertions.assertEquals(employee.getSalary(), saved.getSalary());
    }

    @Test
    public void update() {
        String expected = "updated";

        Employee request = new Employee();
        request.setId("1bf3eb98-e5d4-11ec-8fea-0242ac120001");
        request.setName("updated");

        Employee updated = employeeService.update(request);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(expected, updated.getName());
    }

    @Test
    public void findById() {
        String id = "1bf3eb98-e5d4-11ec-8fea-0242ac120001";
        String expected = "hello";

        Employee foundEmployee = employeeService.findById(id);

        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals(expected, foundEmployee.getName());
    }

    @Test
    public void list() {
        int expectedSize = 5;

        ListResponse<Employee> list = employeeService.list(0, 10);

        Assertions.assertEquals(expectedSize, list.getData().size());
        Assertions.assertEquals(expectedSize, list.getTotalCount());
    }

    @Test
    public void deleteById() {
        String id = "1bf3eb98-e5d4-11ec-8fea-0242ac120001";

        employeeService.deleteById(id);

        Optional<Employee> byId = employeeRepository.findById(id);

        Assertions.assertTrue(byId.isEmpty());
    }

}
