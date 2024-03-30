package com.example.expense.tracker.Repository;

import java.util.UUID;

import com.example.expense.tracker.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, UUID> {

}
