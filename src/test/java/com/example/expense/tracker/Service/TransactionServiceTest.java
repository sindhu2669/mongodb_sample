package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.TransactionRepository;
import com.example.expense.tracker.model.Transaction;
import com.example.expense.tracker.model.TransactionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        // Mocking repository behavior
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(new Transaction(), new Transaction()));

        // Calling the service method
        List<Transaction> transactions = transactionService.getAllTransactions();

        // Verifying the result
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
    }

    @Test
    void testGetTransactionById() {
        // Mocking repository behavior
        when(transactionRepository.findById("1")).thenReturn(Optional.of(new Transaction()));

        // Calling the service method
        Transaction transaction = transactionService.getTransactionById("1");

        // Verifying the result
        assertNotNull(transaction);
    }

    @Test
    void testCreateTransaction() {
        // Mocking repository behavior
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Creating a transaction request
        TransactionRequest request = new TransactionRequest();
        request.setDescription("Test Description");
        request.setAmount(100.0);
        request.setCategory("Test Category");
        request.setType("Test Type");

        // Calling the service method
        Transaction createdTransaction = transactionService.createTransaction(request);

        // Verifying the result
        assertNotNull(createdTransaction);
        assertEquals("Test Description", createdTransaction.getDescription());
    }

    @Test
    void testUpdateTransaction() {
        // Mocking repository behavior
        when(transactionRepository.existsById("1")).thenReturn(true);
        when(transactionRepository.findById("1")).thenReturn(Optional.of(new Transaction()));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Creating a transaction request
        TransactionRequest request = new TransactionRequest();
        request.setDescription("Updated Description");
        request.setAmount(200.0);
        request.setCategory("Updated Category");
        request.setType("Updated Type");

        // Calling the service method
        Transaction updatedTransaction = transactionService.updateTransaction("1", request);

        // Verifying the result
        assertNotNull(updatedTransaction);
        assertEquals("Updated Description", updatedTransaction.getDescription());
        assertEquals(200.0, updatedTransaction.getAmount());
    }

    @Test
    void testDeleteTransaction() {
        // Mocking repository behavior
        when(transactionRepository.existsById("1")).thenReturn(true);

        // Calling the service method
        boolean result = transactionService.deleteTransaction("1");

        // Verifying the result
        assertTrue(result);
        verify(transactionRepository, times(1)).deleteById("1");
    }
}
