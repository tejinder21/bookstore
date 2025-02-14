package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Book s1 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21");
			Book s2 = new Book("Animal Farm", "George Orwell", 1945, "2212343-5");

			repository.save(s1);
			repository.save(s2);

			System.out.println("Demo books have been added to the database.");

		};
	}

}
