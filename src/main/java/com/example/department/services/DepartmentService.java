package com.example.department.services;

import com.example.department.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartments();

    Department addOrUpdateDepartment(Department department);

    boolean deleteDepartment(int id);

    Department getDepartment(int id);
}
