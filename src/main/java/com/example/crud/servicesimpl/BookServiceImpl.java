package com.example.crud.servicesimpl;

import com.example.crud.models.Book;
import com.example.crud.repositories.BookRepository;
import com.example.crud.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(b -> books.add(b));
        return books;
    }

    @Override
    public Book addOrUpdateBook(Book book) {
        try
        {
            return bookRepository.save(book);
        }
        catch (DataIntegrityViolationException dive)
        {
            log.info(dive.toString());
            return new Book();
        }
    }

    @Override
    public boolean deleteBook(int id) {
        try{
            bookRepository.deleteById(id);
            return true;
        }
        catch (EmptyResultDataAccessException erdae)
        {
            log.info(erdae.toString());
            return false;
        }
    }
}
