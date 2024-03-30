package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.mongodbRepository;
import com.example.expense.tracker.model.MessageMongo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageServiceTest {

    @Mock
    private mongodbRepository mongoDBRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void testSaveMessage() {
        // Arrange
        MessageMongo message = new MessageMongo();
        // Set up any necessary data for the message

        // Act
        messageService.saveMessage(message);

        // Assert
        Mockito.verify(mongoDBRepository, Mockito.times(1)).save(message);
    }
}
