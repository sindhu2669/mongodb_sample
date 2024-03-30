package com.example.expense.tracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class MessageMongo {
    @Id
    private String id;
    private String content;


    public MessageMongo() {
    }
}
