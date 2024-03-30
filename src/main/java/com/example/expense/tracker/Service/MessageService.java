package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.mongodbRepository;
import com.example.expense.tracker.model.MessageMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private mongodbRepository mongoDBRepository;

    public void saveMessage(MessageMongo message) {
        mongoDBRepository.save(message);
    }
}
