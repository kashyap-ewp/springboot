package com.library.services;

import com.library.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    Student addOrUpdateStudent(Student student);

    boolean deleteStudent(int id);
}
