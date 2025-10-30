package com.example.library.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    String role;//USER or ADMIN
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<BorrowedRecord> record;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BorrowedRecord> getRecord() {
        return record;
    }

    public void setRecord(List<BorrowedRecord> record) {
        this.record = record;
    }

    public User(String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }
}
