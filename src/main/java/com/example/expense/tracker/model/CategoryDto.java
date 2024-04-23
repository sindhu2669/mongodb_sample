package com.example.expense.tracker.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(ObjectId id, String name) {
    }
}
