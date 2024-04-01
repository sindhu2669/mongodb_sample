package com.example.expense.tracker.integrationTest;

import com.example.expense.tracker.ExpenseTrackerApplication;
import com.example.expense.tracker.model.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExpenseTrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateTransaction() {
        // Create a transaction request object
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setCategory("Test Category");
        transaction.setDescription("Test Description");
        transaction.setType("Test Type");

        // Send HTTP POST request to create a new transaction
        ResponseEntity<Transaction> response = restTemplate.postForEntity(createURLWithPort("/home/api/transaction"), transaction, Transaction.class);

        // Verify the response
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getId()); // Assuming the created transaction has an ID
    }

    @Test
    public void testGetAllTransactions() {
        // Send HTTP GET request to fetch all transactions
        ResponseEntity<Transaction[]> response = restTemplate.getForEntity(createURLWithPort("/home/api/transaction"), Transaction[].class);

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetTransactionById() {
        // Send HTTP GET request to fetch a transaction by ID
        String id = "1"; // Assuming there's a transaction with ID 1
        ResponseEntity<Transaction> response = restTemplate.getForEntity(createURLWithPort("/home/api/transaction/" + id), Transaction.class);

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(id, response.getBody().getId()); // Assuming the fetched transaction has the correct ID
    }

    @Test
    public void testUpdateTransaction() {
        // Send HTTP PUT request to update an existing transaction
        String id = "1"; // Assuming there's a transaction with ID 1
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setAmount(200.0);
        updatedTransaction.setCategory("Updated Category");
        updatedTransaction.setDescription("Updated Description");
        updatedTransaction.setType("Updated Type");

        restTemplate.put(createURLWithPort("/home/api/transaction/" + id), updatedTransaction);

        // Assuming no specific verification needed after updating the transaction
    }

    @Test
    public void testDeleteTransaction() {
        // Send HTTP DELETE request to delete an existing transaction
        String id = "1"; // Assuming there's a transaction with ID 1
        restTemplate.delete(createURLWithPort("/home/api/transaction/" + id));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
