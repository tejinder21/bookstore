package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
        return (args) -> {
            
           
            Category cat1 = new Category("Sci-Fi");
            Category cat2 = new Category("Fantasy");

            // Tallennetaan kategoriat
            categoryRepository.save(cat1);
            categoryRepository.save(cat2);

            // Luodaan ja tallennetaan kirjoja
            Book s1 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", cat1);
            Book s2 = new Book("Animal Farm", "George Orwell", 1945, "2212343-5", cat2);

            bookRepository.save(s1);
            bookRepository.save(s2);

            
            System.out.println("Categories and books have been added to the database.");
            System.out.println("Book 1: " + s1);
            System.out.println("Book 2: " + s2);
        };
    }
}
