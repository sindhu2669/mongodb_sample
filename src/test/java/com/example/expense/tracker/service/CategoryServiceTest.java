package com.example.expense.tracker.service;

import com.example.expense.tracker.model.CategoryDto;
import com.example.expense.tracker.repository.CategoryRepository;
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
        CategoryRequest categoryRequest = new CategoryRequest("Food");
        Category category = new Category("Food");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(categoryRequest);

        assertEquals("Food", createdCategory.getName());
    }

    @Test
    void testUpdateCategory() {
        // Mock data
        String categoryName = "Food";
        CategoryRequest categoryRequest = new CategoryRequest("Updated Food");
        Category existingCategory = new Category(categoryName);

        when(categoryRepository.findByName(categoryName)).thenReturn(existingCategory);
        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        Category updatedCategory = categoryService.updateCategory(categoryName, categoryRequest);

        assertEquals("Updated Food", updatedCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        String categoryName = "Food";
        Category existingCategory = new Category(categoryName);

        when(categoryRepository.findByName(categoryName)).thenReturn(existingCategory);

        boolean deleted = categoryService.deleteCategory(categoryName);

        assertTrue(deleted);
        verify(categoryRepository, times(1)).delete(existingCategory);
    }

    @Test
    void testDeleteCategory_NotFound() {
        String categoryName = "Nonexistent Category";

        when(categoryRepository.findByName(categoryName)).thenReturn(null);

        boolean deleted = categoryService.deleteCategory(categoryName);

        assertFalse(deleted);
        verify(categoryRepository, never()).delete(any(Category.class));
    }

    @Test
    void testGetAllCategories() {

        Category category1 = new Category("Category1");
        Category category2 = new Category("Category2");
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
    }

    @Test
    void testGetCategoryByName() {

        String categoryName = "Food";
        Category expectedCategory = new Category(categoryName);

        when(categoryRepository.findByName(categoryName)).thenReturn(expectedCategory);

        Category result = categoryService.getCategoryByName(categoryName);

        assertEquals(expectedCategory, result);
    }
}
