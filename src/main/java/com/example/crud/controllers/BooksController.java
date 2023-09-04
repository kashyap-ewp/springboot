package com.example.crud.controllers;

import com.example.crud.models.Book;
import com.example.crud.models.ResponseDto;
import com.example.crud.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    BookService bookService;

    @PostMapping("/")
    public ResponseEntity<ResponseDto> addBook(@RequestBody Book book)
    {
        Book b = bookService.addOrUpdateBook(book);
        ResponseDto res = new ResponseDto();

        if(b.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data not Saved, Please Enter Unique Book Name");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(b);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto> getBooks()
    {
        List<Book> books = bookService.getBooks();
        ResponseDto res = new ResponseDto();

        if(books.isEmpty())
        {
            res.setStatus(false);
            res.setMessage("No Data");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            res.setStatus(true);
            res.setData(books);

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateBook(@RequestBody Book book)
    {
        Book b = bookService.addOrUpdateBook(book);
        ResponseDto res = new ResponseDto();

        if(b.getId() == 0)
        {
            res.setStatus(false);
            res.setMessage("Data does not exists");

            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            res.setStatus(true);
            res.setMessage("Data Saved");
            res.setData(b);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@PathVariable int id)
    {
        boolean isDeleted = bookService.deleteBook(id);
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

    @GetMapping("/header")
    public String header(@RequestHeader("Cache") String cache)
    {
        return cache;
    }
    @PatchMapping("/patch")
    public ResponseEntity<ResponseDto> patchBook(@RequestBody Book book)
    {
        return updateBook(book);
    }
}
