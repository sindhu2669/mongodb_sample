package com.example.expense.tracker.RestController;

import com.example.expense.tracker.model.Category;
import com.example.expense.tracker.model.CategoryRequest;
import com.example.expense.tracker.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCategory() {
        CategoryRequest request = new CategoryRequest("Test Category");
        Category createdCategory = new Category("Test Category");
        when(categoryService.createCategory(request)).thenReturn(createdCategory);

        ResponseEntity<Category> responseEntity = categoryController.createCategory(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdCategory, responseEntity.getBody());
    }

    @Test
    void testUpdateCategory() {
        CategoryRequest request = new CategoryRequest("Updated Test Category");
        Category updatedCategory = new Category("Updated Test Category");
        when(categoryService.updateCategory(anyString(), any())).thenReturn(updatedCategory);

        ResponseEntity<Category> responseEntity = categoryController.updateCategory("Test Category", request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedCategory, responseEntity.getBody());
    }

    @Test
    void testDeleteCategory() {
        when(categoryService.deleteCategory(anyString())).thenReturn(true);

        ResponseEntity<Void> responseEntity = categoryController.deleteCategory("Test Category");

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(categoryService, times(1)).deleteCategory(anyString());
    }

    @Test
    void testGetAllCategories() {
        Category category1 = new Category("Category 1");
        Category category2 = new Category("Category 2");
        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categories, responseEntity.getBody());
    }

    @Test
    void testGetCategoryByName() {
        Category category = new Category("Test Category");
        when(categoryService.getCategoryByName(anyString())).thenReturn(category);

        ResponseEntity<Category> responseEntity = categoryController.getCategoryByName("Test Category");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(category, responseEntity.getBody());
    }
}
