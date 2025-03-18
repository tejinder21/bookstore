package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin  //  Salli eri alkuperäiset pyynnöt (CORS)
@RestController  //  REST-kontrolleri, palauttaa JSON-dataa
@RequestMapping("/api/books")  //  REST-endpointin peruspolku
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    //  1. Palauta kaikki kirjat JSON-muodossa
    @GetMapping
    public @ResponseBody List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    //  2. Palauta yksi kirja ID:n perusteella
    @GetMapping("/{id}")
    public @ResponseBody Optional<Book> getBookById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }

    //  3. Tallenna uusi kirja
    @PostMapping
    public @ResponseBody Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
}
