package com.example.expense.tracker.RestController;

import com.example.expense.tracker.model.Transaction;
import com.example.expense.tracker.model.TransactionRequest;
import com.example.expense.tracker.Service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        when(transactionService.getAllTransactions()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> responseEntity = transactionController.getAllTransactions();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void testGetTransactionById() {
        String id = "1";
        Transaction transaction = new Transaction();
        transaction.setId(String.valueOf(Long.valueOf(id)));

        // Mocking the behavior of transactionRepository.findById
        when(transactionService.getTransactionById(id)).thenReturn(transaction);

        ResponseEntity<Transaction> responseEntity = transactionController.getTransactionById(Long.valueOf(id));

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Transaction responseBody = responseEntity.getBody();
        assertNotNull(responseBody); // Ensure response body is not null
        assertEquals(id, String.valueOf(responseBody.getId()), "ID mismatch: Expected " + id + " but got " + responseBody.getId());
    }



    @Test
    void testCreateTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        Transaction transaction = new Transaction();

        when(transactionService.createTransaction(transactionRequest)).thenReturn(transaction);

        ResponseEntity<Transaction> responseEntity = transactionController.createTransaction(transactionRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
    }

    @Test
    void testUpdateTransaction() {
        String id = "1";
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setDescription("Updated Description");
        transactionRequest.setAmount(100.0);
        transactionRequest.setCategory("Updated Category");
        transactionRequest.setType("Updated Type");

        Transaction existingTransaction = new Transaction();
        existingTransaction.setId(String.valueOf(Long.valueOf(id)));
        when(transactionService.updateTransaction(id, transactionRequest)).thenReturn(existingTransaction);

        ResponseEntity<Transaction> responseEntity = transactionController.updateTransaction(Long.valueOf(id), transactionRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(existingTransaction, responseEntity.getBody());
    }

    @Test
    void testDeleteTransaction() {
        String id = "1";

        when(transactionService.deleteTransaction(id)).thenReturn(true);

        ResponseEntity<?> responseEntity = transactionController.deleteTransaction(Long.valueOf(id));

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
