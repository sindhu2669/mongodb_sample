package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.TransactionRepository;
import com.example.expense.tracker.model.Transaction;
import com.example.expense.tracker.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(UUID.fromString(id)).orElse(null);
    }

    public Transaction createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setType(transactionRequest.getType());
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(String id, TransactionRequest transactionRequest) {
        // Check if the transaction exists
        if (!transactionRepository.existsById(UUID.fromString(id))) {
            // If transaction doesn't exist, return null
            return null;
        }

        // If transaction exists, update its properties and save
        Transaction existingTransaction = transactionRepository.findById(UUID.fromString(id)).orElse(null);
        existingTransaction.setDescription(transactionRequest.getDescription());
        existingTransaction.setAmount(transactionRequest.getAmount());
        existingTransaction.setCategory(transactionRequest.getCategory());
        existingTransaction.setType(transactionRequest.getType());
        return transactionRepository.save(existingTransaction);
    }

    public boolean deleteTransaction(String id) {
        if (transactionRepository.existsById(UUID.fromString(id))) {
            transactionRepository.deleteById(UUID.fromString(id));
            return true;
        }
        return false;
    }
}
