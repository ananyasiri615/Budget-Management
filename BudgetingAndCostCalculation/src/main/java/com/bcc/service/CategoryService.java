package com.bcc.service;

import com.bcc.entity.Category;
import com.bcc.repository.CategoryRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(int categoryId, Category updatedCategory) {
        if (categoryRepository.existsById(categoryId)) {
            updatedCategory.setCategoryId(categoryId);
            return categoryRepository.save(updatedCategory);
        } else {
            // Handle category not found
            return null;
        }
    }

    public boolean deleteCategory(int categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        } else {
            // Handle category not found
            return false;
        }
    }
}

