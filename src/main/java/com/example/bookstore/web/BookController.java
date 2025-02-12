package com.example.bookstore.web;

import com.example.bookstore.domain.BookRepository;  // Import BookRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;  // Inject BookRepository

    @RequestMapping("/Booklist")
    public String Booklist(Model model) {
        model.addAttribute("books", repository.findAll());  // Fetch books from database
        return "Booklist";  // Return the Thymeleaf template name
    }
}
