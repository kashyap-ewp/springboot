package com.example.department.controllers;

import com.example.department.models.Department;
import com.example.department.models.Employee;
import com.example.department.models.ResponseDto;
import com.example.department.services.DepartmentService;
import com.example.department.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addEmployee(@RequestBody Employee employee)
    {
        Employee e = employeeService.addOrUpdateEmployee(employee);
        ResponseDto res = new ResponseDto();

        if(e.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please enter correct details");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(e);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getEmployees()
    {
        List<Employee> employees = employeeService.getEmployees();
        ResponseDto res = new ResponseDto();

        if(employees.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(employees);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateEmployee(@RequestBody Employee employee)
    {
        Employee e = employeeService.addOrUpdateEmployee(employee);
        ResponseDto res = new ResponseDto();

        if(e.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Department not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Department Saved");
            res.setData(e);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable int id)
    {
        boolean isDeleted = employeeService.deleteEmployee(id);
        ResponseDto res = new ResponseDto();

        if(!isDeleted){
            res.setStatus(false);
            res.setMessage("Department not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setMessage("Department Deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
