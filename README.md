📚 Library Management System 
A complete Library Management System built using Spring Boot, Spring Security, and Thymeleaf.
It provides separate dashboards for Admin and User roles to manage books, authors, and borrowing activities efficiently.

🚀 Features

🔐 Authentication & Authorization — Secure login and registration using Spring Security.
📚 Book Management — Admin can add, update, delete, and view books.
👩‍💼 Admin Dashboard — Manage books, authors, categories, and users.
👤 User Dashboard — Browse books and track borrowed records.
🧾 Borrowing Records — Record and manage book borrow/return operations.
🎨 Thymeleaf Templates — Responsive HTML & CSS frontend design.
🧩 Tech Stack

| Layer          | Technologies                                  |
| -------------- | --------------------------------------------- |
| **Backend**    | Spring Boot, Spring Security, Spring Data JPA |
| **Frontend**   | Thymeleaf, HTML, CSS                          |
| **Database**   | MySQL (or H2 for testing)                     |
| **Build Tool** | Maven                                         |


com.example.library
├── config
│   └── SecurityConfig.java
├── controller
│   ├── AdminController.java
│   ├── AuthController.java
│   └── UserController.java
├── models
│   ├── Author.java
│   ├── Book.java
│   ├── BorrowedRecord.java
│   ├── Category.java
│   └── User.java
├── repositories
│   ├── AuthorRepository.java
│   ├── BookRepository.java
│   ├── BorrowedRecordRepository.java
│   ├── CategoryRepository.java
│   └── UserRepository.java
└── resources
    ├── static/
    │   └── static.css
    └── templates/
        ├── admin-dashboard.html
        ├── user-dashboard.html
        ├── books.html
        ├── login.html
        └── register.html
