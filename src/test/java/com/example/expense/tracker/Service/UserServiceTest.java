package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.UserRepository;
import com.example.expense.tracker.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Reset mock before each test method
        Mockito.reset(userRepository);
    }

    @Test
    public void testAuthenticate_ValidCredentials_ReturnsTrue() {
        // Arrange
        String username = "testuser";
        String password = "password";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByUsernameAndPassword(username, password)).thenReturn(true);

        // Act
        boolean result = userService.authenticate(username, password);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testAuthenticate_InvalidCredentials_ReturnsFalse() {
        // Arrange
        String username = "testuser";
        String password = "password";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByUsernameAndPassword(username, password)).thenReturn(false);

        // Act
        boolean result = userService.authenticate(username, password);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testUsernameExists_ExistingUsername_ReturnsTrue() {
        // Arrange
        String existingUsername = "existingUser";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByUsername(existingUsername)).thenReturn(true);

        // Act
        boolean result = userService.usernameExists(existingUsername);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testUsernameExists_NonExistingUsername_ReturnsFalse() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // Act
        boolean result = userService.usernameExists(nonExistingUsername);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEmailExists_ExistingEmail_ReturnsTrue() {
        // Arrange
        String existingEmail = "existing@example.com";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByEmail(existingEmail)).thenReturn(true);

        // Act
        boolean result = userService.emailExists(existingEmail);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testEmailExists_NonExistingEmail_ReturnsFalse() {
        // Arrange
        String nonExistingEmail = "nonExisting@example.com";
        // Mock UserRepository behavior
        Mockito.when(userRepository.existsByEmail(nonExistingEmail)).thenReturn(false);

        // Act
        boolean result = userService.emailExists(nonExistingEmail);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User newUser = new User("testuser", "test@example.com", "password");

        // Act
        userService.createUser(newUser);

        // Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);
    }
}
