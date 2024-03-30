package com.example.expense.tracker.model;

import java.util.Date;

public class TransactionHistory {
    private Date date;
    private String description;
    private double amount;
    private String type; // Credit or Debit

    // Constructors
    public TransactionHistory() {
        this.date = new Date(); // Initialize date with current date
    }

    public TransactionHistory(Date date, String description, double amount, String type) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString method (optional)
    @Override
    public String toString() {
        return "TransactionHistory{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
