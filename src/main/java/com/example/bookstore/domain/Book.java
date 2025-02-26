package com.example.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private int publicationYear;
    private String isbn;

    
    @ManyToOne
    @JoinColumn(name = "category_id")  
    private Category category;

    
    public Book() {}

   
    public Book(String title, String author, int publicationYear, String isbn, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.category = category;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Category getCategory() {
        return category;  
    }

    public void setCategory(Category category) {
        this.category = category; 
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author +
                ", year=" + publicationYear + ", isbn=" + isbn + ", category="
                + (category != null ? category.getName() : "None") + "]";
    }
}
