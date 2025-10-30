package com.example.library.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class BorrowedRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
    @ManyToOne
    @JoinColumn(name="book_id")
    Book book;
    @Column(nullable = false)
    LocalDate dueDate;
    @Column(nullable = false)
    LocalDate borrowDate;
    boolean returned=false;

    public BorrowedRecord() {
    }

    public BorrowedRecord(User user, Book book, LocalDate dueDate, LocalDate borrowDate, boolean returned) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.borrowDate = borrowDate;
        this.returned = returned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book books) {
        this.book = books;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
