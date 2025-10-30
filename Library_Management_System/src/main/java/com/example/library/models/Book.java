package com.example.library.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
@ManyToOne @JoinColumn(name="author_id")
    private Author author;
@ManyToOne@JoinColumn(name="category_id")
    private Category category;
    private boolean borrowed=false;
    @OneToMany(mappedBy="book",cascade = CascadeType.ALL)
    List<BorrowedRecord> records;

    public Book() {
    }

    public Book(String title, Author author, Category category, boolean borrowed) {
        this.title = title;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public List<BorrowedRecord> getRecords() {
        return records;
    }

    public void setRecords(List<BorrowedRecord> records) {
        this.records = records;
    }
}
