package com.example.expense.tracker.RestController;

import com.example.expense.tracker.Service.TransactionService;
import com.example.expense.tracker.model.Transaction;
import com.example.expense.tracker.model.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Transaction Management APIs
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions(); // Change to transactionService
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(String.valueOf(id)); // Change to transactionService
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.createTransaction(transactionRequest); // Change to transactionService
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        Transaction updatedTransaction = transactionService.updateTransaction(String.valueOf(id), transactionRequest); // Change to transactionService
        if (updatedTransaction != null) {
            return ResponseEntity.ok(updatedTransaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransaction(String.valueOf(id)); // Change to transactionService
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Other transaction-related APIs can be added here
}
