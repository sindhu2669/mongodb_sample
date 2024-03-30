package com.example.expense.tracker.model;

import lombok.Getter;

@Getter
public class TransactionRequest {
    private String description;
    private double amount;
    private String category;
    private String type;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Constructors, getters, and setters
    // You can generate constructors, getters, and setters using your IDE or lombok.
}
