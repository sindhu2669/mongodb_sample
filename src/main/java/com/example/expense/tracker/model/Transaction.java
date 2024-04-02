package com.example.expense.tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String id;
    private double amount;
    private String category;
    private String description;
    private String type;

    public Transaction() {
    }

    public Transaction(double amount, String category, String description, String type) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.type = type;
    }
}
