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
        Transaction transaction = new Transaction();
        transaction.setAmount(100.0);
        transaction.setCategory("Test Category");
        transaction.setDescription("Test Description");
        transaction.setType("Test Type");

        ResponseEntity<Transaction> response = restTemplate.postForEntity(createURLWithPort("/home/api/transaction"), transaction, Transaction.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getId());
    }

    @Test
    public void testGetAllTransactions() {
        ResponseEntity<Transaction[]> response = restTemplate.getForEntity(createURLWithPort("/home/api/transaction"), Transaction[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetTransactionById() {
        String id = "1";
        ResponseEntity<Transaction> response = restTemplate.getForEntity(createURLWithPort("/home/api/transaction/" + id), Transaction.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testUpdateTransaction() {
        String id = "1";
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setAmount(200.0);
        updatedTransaction.setCategory("Updated Category");
        updatedTransaction.setDescription("Updated Description");
        updatedTransaction.setType("Updated Type");

        restTemplate.put(createURLWithPort("/home/api/transaction/" + id), updatedTransaction);

    }

    @Test
    public void testDeleteTransaction() {
        String id = "1";
        restTemplate.delete(createURLWithPort("/home/api/transaction/" + id));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
