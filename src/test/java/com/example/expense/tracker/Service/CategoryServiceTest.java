package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.CategoryRepository;
import com.example.expense.tracker.model.Category;
import com.example.expense.tracker.model.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCategory() {
        // Mock data
        CategoryRequest categoryRequest = new CategoryRequest("Food");
        Category category = new Category("Food");

        // Mock repository behavior
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Call service method
        Category createdCategory = categoryService.createCategory(categoryRequest);

        // Verify the result
        assertEquals("Food", createdCategory.getName());
    }

    @Test
    void testUpdateCategory() {
        // Mock data
        String categoryName = "Food";
        CategoryRequest categoryRequest = new CategoryRequest("Updated Food");
        Category existingCategory = new Category(categoryName);

        // Mock repository behavior
        when(categoryRepository.findByName(categoryName)).thenReturn(existingCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        // Call service method
        Category updatedCategory = categoryService.updateCategory(categoryName, categoryRequest);

        // Verify the result
        assertEquals("Updated Food", updatedCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        // Mock data
        String categoryName = "Food";
        Category existingCategory = new Category(categoryName);

        // Mock repository behavior
        when(categoryRepository.findByName(categoryName)).thenReturn(existingCategory);

        // Call service method
        boolean deleted = categoryService.deleteCategory(categoryName);

        // Verify the result
        assertTrue(deleted);
        verify(categoryRepository, times(1)).delete(existingCategory);
    }

    @Test
    void testDeleteCategory_NotFound() {
        // Mock data
        String categoryName = "Nonexistent Category";

        // Mock repository behavior
        when(categoryRepository.findByName(categoryName)).thenReturn(null);

        // Call service method
        boolean deleted = categoryService.deleteCategory(categoryName);

        // Verify the result
        assertFalse(deleted);
        verify(categoryRepository, never()).delete(any(Category.class));
    }

    @Test
    void testGetAllCategories() {
        // Mock data
        Category category1 = new Category("Category1");
        Category category2 = new Category("Category2");
        List<Category> categories = Arrays.asList(category1, category2);

        // Mock repository behavior
        when(categoryRepository.findAll()).thenReturn(categories);

        // Call service method
        List<Category> result = categoryService.getAllCategories();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    void testGetCategoryByName() {
        // Mock data
        String categoryName = "Food";
        Category expectedCategory = new Category(categoryName);

        // Mock repository behavior
        when(categoryRepository.findByName(categoryName)).thenReturn(expectedCategory);

        // Call service method
        Category result = categoryService.getCategoryByName(categoryName);

        // Verify the result
        assertEquals(expectedCategory, result);
    }
}
