package com.example.crud.services;

import com.example.crud.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book addOrUpdateBook(Book book);

    boolean deleteBook(int id);
}
