package com.oktaykcr.keycloakspringboot.service;

import com.oktaykcr.keycloakspringboot.common.ListResponse;
import com.oktaykcr.keycloakspringboot.common.NullAwareBeanUtilsBean;
import com.oktaykcr.keycloakspringboot.entity.Employee;
import com.oktaykcr.keycloakspringboot.exception.common.InternalServerException;
import com.oktaykcr.keycloakspringboot.exception.employee.EmployeeNotFoundException;
import com.oktaykcr.keycloakspringboot.repository.EmployeeRepository;
import com.oktaykcr.keycloakspringboot.service.base.BaseCrudService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class EmployeeService extends BaseCrudService<Employee> {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        BeanUtilsBean notNull = new NullAwareBeanUtilsBean();

        Employee foundEmployee = getEmployeeById(employee.getId());

        try {
            notNull.copyProperties(foundEmployee, employee);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InternalServerException(e.getMessage());
        }

        return employeeRepository.save(foundEmployee);
    }

    @Override
    public ListResponse<Employee> list(Integer pageNumber, Integer pageOffset) {
        ListResponse<Employee> listResponse = new ListResponse<>();

        Pageable pageable = PageRequest.of(pageNumber, pageOffset);
        listResponse.setData(employeeRepository.findAll(pageable).getContent());
        listResponse.setTotalCount(employeeRepository.count());
        return listResponse;
    }

    @Override
    public Employee findById(String id) {
        return getEmployeeById(id);
    }

    @Override
    public void deleteById(String id) {
        Employee foundEmployee = getEmployeeById(id);
        employeeRepository.delete(foundEmployee);
    }

    private Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(String.format("Employee: %s not found", id));
                    return new EmployeeNotFoundException(id);
                });
    }

}
