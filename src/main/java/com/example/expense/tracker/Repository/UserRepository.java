package com.example.expense.tracker.Repository;

import com.example.expense.tracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
