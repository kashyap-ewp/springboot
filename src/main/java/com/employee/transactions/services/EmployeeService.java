package com.employee.transactions.services;

import com.employee.transactions.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addOrUpdateEmployee(Employee employee);

    boolean deleteEmployee(int id);

}
