package com.example.library.controller;

import com.example.library.models.Book;
import com.example.library.models.BorrowedRecord;
import com.example.library.models.User;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.BorrowedRecordRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    BookRepository bookrepo;
    @Autowired
    BorrowedRecordRepository recordRepository;
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        User user = userRepo.findUserByUsername(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("books", bookrepo.findAll());
        List<BorrowedRecord> record = recordRepository.findByUserIdAndReturnedFalse(user.getId());
        model.addAttribute("borrows", record);
        return "user-dashboard";
    }
    @PostMapping ("/borrow")
    public String borrow(@RequestParam Long bookId,Authentication auth)
    {
        User user=userRepo.findUserByUsername(auth.getName());
        Book book=bookrepo.findById(bookId).orElse(null);
        if(book!=null && !book.isBorrowed()) {
            BorrowedRecord borrow = new BorrowedRecord();
            borrow.setBook(book);
            borrow.setUser(user);
            borrow.setBorrowDate(LocalDate.now());
            borrow.setDueDate(LocalDate.now().plusWeeks(2));
            borrow.setReturned(false);
            recordRepository.save(borrow);
            book.setBorrowed(true);
            bookrepo.save(book);
        }
        return "redirect:/user/dashboard";

        }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long recordId) {

        BorrowedRecord record = recordRepository.findById(recordId).orElse(null);
        if (record != null && !record.isReturned()) {
            record.setReturned(true);
            recordRepository.save(record);

            Book book = record.getBook();
            book.setBorrowed(false);
            bookrepo.save(book);
        }
        return "redirect:/user/dashboard";
    }
    @GetMapping("/search")
    public String search(@RequestParam String query,Model model,Authentication auth)
    {
        User user=userRepo.findUserByUsername(auth.getName());
        List<Book> books=bookrepo.searchByTitleOrAuthorOrCategory(query);
        List<BorrowedRecord> records=recordRepository.findByUserIdAndReturnedFalse(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("books",books);
        model.addAttribute("borrows",records);
        model.addAttribute("searchQuery",query);
        return "user-dashboard";
    }
}






