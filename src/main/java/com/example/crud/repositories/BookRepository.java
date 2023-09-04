package com.example.crud.repositories;

import com.example.crud.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
