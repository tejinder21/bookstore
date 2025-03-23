package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateAndFindBook() {
        Book book = new Book("Test Book", "Test Author", 2024, "123456789", null);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
    }

    @Test
    void testDeleteBook() {
        Book book = new Book("Delete Me", "Author", 2024, "987654321", null);
        bookRepository.save(book);
        Long bookId = book.getId();

        bookRepository.deleteById(bookId);

        assertThat(bookRepository.findById(bookId)).isEmpty();
    }
}