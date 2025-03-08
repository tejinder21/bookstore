package com.example.bookstore.web;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Book list page with authenticated user's name
    @RequestMapping("/Booklist")
    public String bookList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("books", repository.findAll()); // Get all books
        model.addAttribute("username", userDetails.getUsername()); // Display the logged-in user's username
        return "Booklist";  // Ensure the correct view template is returned
    }

    // Page to add a new book
    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book()); // Empty book
        model.addAttribute("categories", categoryRepository.findAll()); // All categories
        return "addbook";  // Ensure addbook.html exists
    }

    // Save the new book
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book); // Save book
        return "redirect:/Booklist";  // Redirect to Booklist after saving
    }

    // Delete book (restricted to admin users)
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        // Check if the user has the "ROLE_ADMIN" role before allowing deletion
        if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            repository.deleteById(id); // Delete book by ID
        }
        return "redirect:/Booklist";  // Redirect to Booklist after deletion
    }

    // Edit an existing book
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", repository.findById(id).orElse(null)); // Find and edit book
        model.addAttribute("categories", categoryRepository.findAll()); // Add categories
        return "editbook";  // Ensure editbook.html exists
    }

    // Login page - Spring Security will use this by default
    @GetMapping("/login")
    public String login() {
        return "login"; // This returns the login.html view
    }
}
