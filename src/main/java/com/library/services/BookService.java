package com.library.services;

import com.library.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book addOrUpdateBook(Book book);

    boolean deleteBook(int id);
}
