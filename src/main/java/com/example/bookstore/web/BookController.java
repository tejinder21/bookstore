package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/Booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());  // Varmista, että kaikki kirjat lähetetään
        return "Booklist";
    }

    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());  // Tyhjä kirja
        model.addAttribute("categories", categoryRepository.findAll());  // Kaikki kategoriat
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);  // Tallenna kirja
        return "redirect:/Booklist";
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);  // Poista kirja
        return "redirect:/Booklist";
    }

    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", repository.findById(id).orElse(null));  // Etsi ja muokkaa kirjaa
        model.addAttribute("categories", categoryRepository.findAll());  // Lisää kategoriat
        return "editbook";
    }
}
