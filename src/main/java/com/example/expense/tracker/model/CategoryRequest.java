package com.example.expense.tracker.model;

public class CategoryRequest {
    private String name;

    // Constructors
    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.name = name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

