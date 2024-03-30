package com.example.expense.tracker;

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
        // Create a category request object
        Category category = new Category();
        category.setName("Test Category");

        // Send HTTP POST request to create a new category
        ResponseEntity<Category> response = restTemplate.postForEntity(createURLWithPort("/home/api/category"), category, Category.class);

        // Verify the response
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertNotNull(response.getBody().getId()); // Assuming the created category has an ID
    }

    @Test
    public void testGetAllCategories() {
        // Send HTTP GET request to fetch all categories
        ResponseEntity<Category[]> response = restTemplate.getForEntity(createURLWithPort("/home/api/category"), Category[].class);

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetCategoryById() {
        // Send HTTP GET request to fetch a category by ID
        String id = "1"; // Assuming there's a category with ID 1
        ResponseEntity<Category> response = restTemplate.getForEntity(createURLWithPort("/home/api/category/" + id), Category.class);

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(id, response.getBody().getId()); // Assuming the fetched category has the correct ID
    }

    @Test
    public void testUpdateCategory() {
        // Send HTTP PUT request to update an existing category
        String id = "1"; // Assuming there's a category with ID 1
        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Category");

        restTemplate.put(createURLWithPort("/home/api/category/" + id), updatedCategory);

        // Assuming no specific verification needed after updating the category
    }

    @Test
    public void testDeleteCategory() {
        // Send HTTP DELETE request to delete an existing category
        String id = "1"; // Assuming there's a category with ID 1
        restTemplate.delete(createURLWithPort("/home/api/category/" + id));

        // Assuming no specific verification needed after deleting the category
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
