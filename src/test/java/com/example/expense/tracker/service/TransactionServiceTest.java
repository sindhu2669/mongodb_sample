package com.example.expense.tracker.service;

import com.example.expense.tracker.repository.TransactionRepository;
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
import java.util.UUID;

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

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(new Transaction(), new Transaction()));

        List<Transaction> transactions = transactionService.getAllTransactions();

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
    }

    @Test
    void testGetTransactionById() {

        when(transactionRepository.findById(UUID.fromString("1"))).thenReturn(Optional.of(new Transaction()));

        Transaction transaction = transactionService.getTransactionById("1");

        assertNotNull(transaction);
    }

    @Test
    void testCreateTransaction() {
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionRequest request = new TransactionRequest();
        request.setDescription("Test Description");
        request.setAmount(100.0);
        request.setCategory("Test Category");
        request.setType("Test Type");

        Transaction createdTransaction = transactionService.createTransaction(request);

        assertNotNull(createdTransaction);
        assertEquals("Test Description", createdTransaction.getDescription());
    }

    @Test
    void testUpdateTransaction() {
        when(transactionRepository.existsById(UUID.fromString("1"))).thenReturn(true);
        when(transactionRepository.findById(UUID.fromString("1"))).thenReturn(Optional.of(new Transaction()));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TransactionRequest request = new TransactionRequest();
        request.setDescription("Updated Description");
        request.setAmount(200.0);
        request.setCategory("Updated Category");
        request.setType("Updated Type");

        Transaction updatedTransaction = transactionService.updateTransaction("1", request);

        assertNotNull(updatedTransaction);
        assertEquals("Updated Description", updatedTransaction.getDescription());
        assertEquals(200.0, updatedTransaction.getAmount());
    }

    @Test
    void testDeleteTransaction() {
        when(transactionRepository.existsById(UUID.fromString("1"))).thenReturn(true);

        boolean result = transactionService.deleteTransaction("1");

        assertTrue(result);
        verify(transactionRepository, times(1)).deleteById(UUID.fromString("1"));
    }
}
