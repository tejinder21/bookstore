package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
 @RequestMapping("/api/custom") // Kaikki REST endpointit alkaa /api
public class BookRestController {

    @Autowired
    private BookRepository repository;

    // Hae kaikki kirjat JSON muodossa
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return (List<Book>) repository.findAll();
    }

    // Hae yksi kirja id perusteella
    @GetMapping("/books/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    

}
