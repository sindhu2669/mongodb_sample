package com.example.expense.tracker.model;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;

    // Getters and setters
}
