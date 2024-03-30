package com.example.expense.tracker.RestController;

import com.example.expense.tracker.Repository.UserRepository;
import com.example.expense.tracker.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.*;

public class MessageControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageController messageController;

    @Test
    public void testHandleSignUp_Success() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Prepare test data
        User user = new User();
        // Populate user data as needed for the test

        // Invoke the method under test
        String viewName = messageController.handleSignUp(user, mock(BindingResult.class), mock(RedirectAttributes.class));

        // Verify that userRepository.save() was called
        verify(userRepository, times(1)).save(user);
        // You can add more assertions here based on the expected behavior of the method

        // Assert the view name returned by the method
        // Add your assertions for the view name if needed
    }
}
