package com.example.expense.tracker.model;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;

    // Constructors
    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.name = name;
    }
}
