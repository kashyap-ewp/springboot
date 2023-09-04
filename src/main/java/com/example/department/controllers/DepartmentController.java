package com.example.department.controllers;

import com.example.department.models.Department;
import com.example.department.models.ResponseDto;
import com.example.department.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addDepartment(@RequestBody Department department)
    {
        Department d = departmentService.addOrUpdateDepartment(department);
        ResponseDto res = new ResponseDto();

        if(d.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please enter correct details");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(d);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getDepartments()
    {
        List<Department> departments = departmentService.getDepartments();
        ResponseDto res = new ResponseDto();

        if(departments.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(departments);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getDepartment(@PathVariable int id)
    {
        Department department = departmentService.getDepartment(id);
        ResponseDto res = new ResponseDto();

        if(department.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Department not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(department);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateDepartment(@RequestBody Department department)
    {
        Department d = departmentService.addOrUpdateDepartment(department);
        ResponseDto res = new ResponseDto();

        if(d.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Department not found");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Department Saved");
            res.setData(d);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteDepartment(@PathVariable int id)
    {
        boolean isDeleted = departmentService.deleteDepartment(id);
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
