package com.example.expense.tracker.Service;

import com.example.expense.tracker.Repository.CategoryRepository;
import com.example.expense.tracker.model.Category;
import com.example.expense.tracker.model.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(String name, CategoryRequest categoryRequest) {
        Category existingCategory = categoryRepository.findByName(name);
        if (existingCategory != null) {
            existingCategory.setName(categoryRequest.getName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    public boolean deleteCategory(String name) {
        Category existingCategory = categoryRepository.findByName(name);
        if (existingCategory != null) {
            categoryRepository.delete(existingCategory);
            return true;
        }
        return false;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
