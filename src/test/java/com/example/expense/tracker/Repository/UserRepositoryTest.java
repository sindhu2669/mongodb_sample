package com.example.expense.tracker.Repository;

import com.example.expense.tracker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testExistsByUsername_WhenUsernameExists_ReturnsTrue() {
        // Arrange
        String existingUsername = "existingUser";
        User user = new User();
        user.setUsername(existingUsername);
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByUsername(existingUsername);

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByUsername_WhenUsernameDoesNotExist_ReturnsFalse() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";

        // Act
        boolean exists = userRepository.existsByUsername(nonExistingUsername);

        // Assert
        assertThat(exists).isFalse();
    }

    @Test
    public void testExistsByEmail_WhenEmailExists_ReturnsTrue() {
        // Arrange
        String existingEmail = "existing@example.com";
        User user = new User();
        user.setEmail(existingEmail);
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByEmail(existingEmail);

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByEmail_WhenEmailDoesNotExist_ReturnsFalse() {
        // Arrange
        String nonExistingEmail = "nonexisting@example.com";

        // Act
        boolean exists = userRepository.existsByEmail(nonExistingEmail);

        // Assert
        assertThat(exists).isFalse();
    }
}
