package com.library.controllers;

import com.library.models.ResponseDto;
import com.library.models.Student;
import com.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<ResponseDto> addStudent(@RequestBody Student student)
    {
        Student s = studentService.addOrUpdateStudent(student);
        ResponseDto res = new ResponseDto();

        if(s.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please Enter correct details");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(s);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping
    public ResponseEntity<ResponseDto> getStudents()
    {
        List<Student> students = studentService.getStudents();
        ResponseDto res = new ResponseDto();

        if(students.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(students);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @PutMapping
    public ResponseEntity<ResponseDto> updateStudent(@RequestBody Student student)
    {
        Student s = studentService.addOrUpdateStudent(student);
        ResponseDto res = new ResponseDto();

        if(s.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data does not exists");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(s);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteStudent(@PathVariable int id)
    {
        boolean isDeleted = studentService.deleteStudent(id);
        ResponseDto res = new ResponseDto();

        if(!isDeleted){
            res.setStatus(false);
            res.setMessage("Data does not exists");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setMessage("Data Deleted");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}
