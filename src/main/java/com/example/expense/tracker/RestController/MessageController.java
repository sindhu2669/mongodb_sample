package com.example.expense.tracker.RestController;

import com.example.expense.tracker.Repository.UserRepository;
import com.example.expense.tracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MessageController {

    private final UserRepository userRepository;

    @Autowired
    public MessageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/home")
    public String handleSignUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // If validation errors exist, return to the signup page
            return "signup";
        }

        try {
            // Save the user to the database
            userRepository.save(user);

            // Add a success message
            redirectAttributes.addFlashAttribute("successMessage", "Sign up successful!");

            // Redirect to the homepage
            return "redirect:/home";
        } catch (Exception e) {
            // If an error occurs, add an error message to the flash attributes and return to the signup page
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred during sign up. Please try again.");
            return "redirect:/home";
        }
    }

    public void handleSignUp(User user) {
    }
}
