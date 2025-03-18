package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {
            // Create categories
            Category cat1 = new Category("Sci-Fi");
            Category cat2 = new Category("Fantasy");

            categoryRepository.save(cat1);
            categoryRepository.save(cat2);

            // Create books
            Book s1 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", cat1);
            Book s2 = new Book("Animal Farm", "George Orwell", 1945, "2212343-5", cat2);

            bookRepository.save(s1);
            bookRepository.save(s2);

            // Create users
            AppUser admin = new AppUser("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN", "admin@bookstore.com");
            AppUser user = new AppUser("user", passwordEncoder.encode("user123"), "ROLE_USER", "user@bookstore.com");

            appUserRepository.save(admin);
            appUserRepository.save(user);
        };
    }
}
