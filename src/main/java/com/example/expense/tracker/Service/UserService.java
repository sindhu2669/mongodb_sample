package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.UserRepository;
import com.example.expense.tracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Replace this with your actual password verification logic
            // For example, you can use a password encoder to match passwords securely
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
    }

    public boolean existsByUsername(String username) {
        return false;
    }

    public boolean existsByEmail(String email) {
        return false;
    }
}
