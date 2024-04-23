package com.example.expense.tracker.integrationTest;

import com.example.expense.tracker.ExpenseTrackerApplication;
import com.example.expense.tracker.model.Category;
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
public class CategoryIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateCategory() {

        Category category = new Category();
        category.setName("Test Category");


        ResponseEntity<Category> response = restTemplate.postForEntity(createURLWithPort("/home/api/category"), category, Category.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getId()); // Assuming the created category has an ID
    }

    @Test
    public void testGetAllCategories() {

        ResponseEntity<Category[]> response = restTemplate.getForEntity(createURLWithPort("/home/api/category"), Category[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetCategoryById() {

        String id = "1";
        ResponseEntity<Category> response = restTemplate.getForEntity(createURLWithPort("/home/api/category/" + id), Category.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testUpdateCategory() {
        String id = "1";
        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Category");

        restTemplate.put(createURLWithPort("/home/api/category/" + id), updatedCategory);

    }

    @Test
    public void testDeleteCategory() {
        String id = "1";
        restTemplate.delete(createURLWithPort("/home/api/category/" + id));

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
