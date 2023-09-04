package com.example.department.services.impl;

import com.example.department.models.Department;
import com.example.department.models.Employee;
import com.example.department.repositories.DepartmentRepository;
import com.example.department.repositories.EmployeeRepository;
import com.example.department.services.DepartmentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(b -> departments.add(b));

        return departments;
    }

    @Override
    public Department addOrUpdateDepartment(Department department) {
        try
        {
            int eid = department.getManager().getId();
            Optional<Employee> e = employeeRepository.findById(eid);
            if(e.isPresent()){
                department.setManager(e.get());
                return departmentRepository.save(department);
            }
            else {
                log.info(eid + " Not Present");
                return new Department();
            }
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Department();
        }
    }

    @Override
    public boolean deleteDepartment(int id) {
        try{
            departmentRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }

    @Override
    public Department getDepartment(int id) {
        try{
            Optional<Department> department = departmentRepository.findById(id);
            if(department.isPresent())
            {
                return department.get();
            }
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
        }
        return new Department();
    }
}
