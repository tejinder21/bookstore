package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Kirjalista
    
    @RequestMapping("/booklist")
    public String bookList(Model model) {
        String username = getCurrentUserName(); // Get the username dynamically
        model.addAttribute("username", username); // Add the username to the model
        model.addAttribute("books", repository.findAll()); // Existing logic
        return "booklist"; 
    }

    // Lisää kirja
    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    // Tallenna kirja
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // Poista kirja
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    // Muokkaa kirja
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        return repository.findById(id)
                .map(book -> {
                    model.addAttribute("book", book);
                    model.addAttribute("categories", categoryRepository.findAll());
                    return "editbook";
                })
                .orElse("redirect:/booklist");
    }

    // Kirjautumisreitti (login)
    @RequestMapping("/login")
    public String loginPage() {
        return "login"; // Palautetaan login.html
    }

    // Kirjautumisen jälkeen tervehdys käyttäjälle
    @RequestMapping("/welcome")
    public String welcomeUser(Model model) {
        String username = getCurrentUserName();
        model.addAttribute("username", username);
        return "welcome"; // Tervetuloa-sivu, jossa näkyy käyttäjänimi
    }

    // Saadaan nykyinen käyttäjänimi
    private String getCurrentUserName() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    // Uloskirjautuminen
    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Kirjautumisen jälkeen palautetaan login-sivulle
    }
}
