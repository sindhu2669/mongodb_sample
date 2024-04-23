package com.example.expense.tracker.repository;

import com.example.expense.tracker.service.CategoryService;
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
    private CategoryService categoryService;

    @Test
    void testFindByName() {
        // Mock data
        String categoryName = "TestCategory";
        Category category = new Category(categoryName);

        when(categoryRepository.findByName(categoryName)).thenReturn(category);

        Category foundCategory = categoryRepository.findByName(categoryName);

        assertEquals(category, foundCategory);
    }

    @Test
    void testFindCategoryByName() {
        // Mock data
        String categoryName = "TestCategory";
        Category category = new Category(categoryName);

        when(categoryRepository.findCategoryByName(categoryName)).thenReturn(category);

        Category foundCategory = categoryRepository.findCategoryByName(categoryName);

        assertEquals(category, foundCategory);
    }
}
