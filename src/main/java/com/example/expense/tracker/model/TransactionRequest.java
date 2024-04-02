package com.example.expense.tracker.model;

import lombok.Data;

@Data
public class TransactionRequest {
    private String description;
    private double amount;
    private String category;
    private String type;
}
