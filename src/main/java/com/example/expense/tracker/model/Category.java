package com.example.expense.tracker.model;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "categories")
public class Category {
    @Id
    private ObjectId id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }

    public void setName(String name) {
        this.name = name;
    }
}
