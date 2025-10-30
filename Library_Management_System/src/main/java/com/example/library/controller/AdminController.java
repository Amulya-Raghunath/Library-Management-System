package com.example.library.controller;

import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.Category;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
@Autowired
    BookRepository bookRepository;
@Autowired
    AuthorRepository authorRepository;
@Autowired
    CategoryRepository categoryRepository;
@GetMapping("/dashboard")
    public String dashboard()
{
    return "admin-dashboard";
}
@GetMapping("/books")
    public String books(Model model)
{
    model.addAttribute("books",bookRepository.findAll());
    model.addAttribute("authors",authorRepository.findAll());
    model.addAttribute("categories",categoryRepository.findAll());
    return "books";
}
@PostMapping("/books/add")
    public String addBook(@RequestParam String title,@RequestParam Long authorId,@RequestParam Long categoryId)
{
    Book book=new Book();
    book.setTitle(title);
    book.setAuthor(authorRepository.findById(authorId).orElse(null));
    book.setCategory(categoryRepository.findById(categoryId).orElse(null));
    bookRepository.save(book);
    return "redirect:/admin/books";
}
@GetMapping("/books/delete")
    public String deleteBook(@RequestParam Long id)
{
    bookRepository.deleteById(id);
    return "redirect:/admin/books";
}
@PostMapping("/authors/add")
    public String addAuthor(@RequestParam String name)
{
    Author author=new Author(name);
    authorRepository.save(author);
    return "redirect:/admin/books";

}
@PostMapping("/categories/add")
    public String addCategory(@RequestParam String name)
{
    Category category=new Category(name);
    categoryRepository.save(category);
    return "redirect:/admin/books";
}
}
