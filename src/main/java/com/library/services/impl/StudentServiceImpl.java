package com.library.services.impl;

import com.library.models.Book;
import com.library.models.Student;
import com.library.repositories.BookRepository;
import com.library.repositories.StudentRepository;
import com.library.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try{
            studentRepository.findAll().forEach(s -> students.add(s));
        }
        catch (Exception e) {
            log.error(e.toString());
        }
        return students;
    }

    @Override
    public Student addOrUpdateStudent(Student student) {
        try
        {
            if(student.getBooks()!=null)
                for(Book b : student.getBooks()) if(!bookRepository.existsById(b.getId())) return new Student();

            return studentRepository.save(student);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Student();
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        try{
            studentRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }
}
