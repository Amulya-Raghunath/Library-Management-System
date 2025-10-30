package com.example.library.controller;

import com.example.library.models.User;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
@Autowired
    UserRepository userRepo;
@Autowired
    PasswordEncoder passwordEncoder;
@GetMapping("/login")
    public String login()
{
    return "login";
}
@GetMapping("/register")
    public String register(Model model)
{
    model.addAttribute("user",new User());
    return "register";
}
@PostMapping("/register")
    public String register(@ModelAttribute User user)
{
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
    return "redirect:/login";
}
@GetMapping("/dashboard")
public String dashboard(Authentication auth, Model model) {
    // Get the username from the Authentication object
    String username = auth.getName();
    model.addAttribute("username", username);

    // Example role-based redirect (adjust role names as needed)
    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
        return "admin-dashboard";
    } else {
        return "user-dashboard";
    }
}

}
