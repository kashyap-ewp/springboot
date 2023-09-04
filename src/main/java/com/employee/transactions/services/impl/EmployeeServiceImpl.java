package com.employee.transactions.services.impl;

import com.employee.transactions.repositories.EmployeeRepository;
import com.employee.transactions.services.EmployeeService;
import com.employee.transactions.models.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(e -> employees.add(e));
        return employees;
    }

    @Override
    public Employee addOrUpdateEmployee(Employee employee) {
        try
        {
            return employeeRepository.save(employee);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Employee();
        }

        catch (JpaObjectRetrievalFailureException jorfe){
            log.info(jorfe.toString());
            return new Employee();
        }
    }

    @Override
    public boolean deleteEmployee(int id) {
        try{
            employeeRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }
}
