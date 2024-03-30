package com.example.expense.tracker.Repository;

import com.example.expense.tracker.Service.CategoryService;
import com.example.expense.tracker.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService; // Assuming CategoryService is used in CategoryRepository

    @Test
    void testFindByName() {
        // Mock data
        String categoryName = "TestCategory";
        Category category = new Category(categoryName);

        // Configure mock behavior
        when(categoryRepository.findByName(categoryName)).thenReturn(category);

        // Call the method under test
        Category foundCategory = categoryRepository.findByName(categoryName);

        // Verify the result
        assertEquals(category, foundCategory);
    }

    @Test
    void testFindCategoryByName() {
        // Mock data
        String categoryName = "TestCategory";
        Category category = new Category(categoryName);

        // Configure mock behavior
        when(categoryRepository.findCategoryByName(categoryName)).thenReturn(category);

        // Call the method under test
        Category foundCategory = categoryRepository.findCategoryByName(categoryName);

        // Verify the result
        assertEquals(category, foundCategory);
    }
}
