package com.example.department.services.impl;

import com.example.department.models.Department;
import com.example.department.models.Employee;
import com.example.department.repositories.DepartmentRepository;
import com.example.department.repositories.EmployeeRepository;
import com.example.department.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;
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
    }

    @Override
    public boolean deleteEmployee(int id) {
        try{
            //employeeRepository.fl
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
