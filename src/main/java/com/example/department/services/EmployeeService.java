package com.example.department.services;

import com.example.department.models.Department;
import com.example.department.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addOrUpdateEmployee(Employee employee);

    boolean deleteEmployee(int id);

}
