package com.example.expense.tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "category")
public class Category {
    @Id
    private UUID id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}
