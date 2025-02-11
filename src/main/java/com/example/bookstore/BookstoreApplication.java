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
			Book s1 = new Book("\"1984\", \"George Orwell\", 1949, \"9780451524935\", 9.99", null, 0, null, 0);
			Book s2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 7.99);
			Book s3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 10.99);

			repository.save(s1);
			repository.save(s2);
			repository.save(s3);

			System.out.println("Demo books have been added to the database.");

		};
	}

}
