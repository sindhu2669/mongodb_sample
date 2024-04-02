package com.example.expense.tracker.model;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionHistory {
    private Date date;
    private String description;
    private double amount;
    private String type;

    public TransactionHistory() {
        this.date = new Date();
    }
}
