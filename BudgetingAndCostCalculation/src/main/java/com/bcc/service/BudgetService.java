package com.bcc.service;

import com.bcc.entity.Budget;
import com.bcc.entity.Category;
import com.bcc.entity.User;
import com.bcc.repository.BudgetRepository;
import com.bcc.repository.CategoryRepository;
import com.bcc.repository.ExpenseRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    @Autowired
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(BudgetRepository budgetRepository, UserRepository userRepository, CategoryRepository categoryRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(int budgetId) {
        return budgetRepository.findById(budgetId).orElse(null);
    }

    public Budget createBudget(int userId, int categoryId, Budget budget) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            // Handle category not found
            return null;
        }
        Budget existingbudget = budgetRepository.findBudgetByUserAndCategory(userId, categoryId);
        if (existingbudget != null) {
            // A budget for the same category already exists
            // Suggest updating the existing budget
            return existingbudget;
        }
        budget.setUser(user);
        budget.setCategory(category);
        budget =  budgetRepository.save(budget);

        // Calculate the total budget for the user and update it
//        double totalBudget = budgetRepository.calculateTotalBudgetForUser(userId);
//        user.setTotalExpenses(totalBudget);
//        userRepository.save(user);

        // Calculate Spending Percentage
        double totalExpensesInCategory = expenseRepository.getTotalExpensesInCategoryForUser(userId, categoryId);
        budget.setTotalExpensesInCategory(totalExpensesInCategory);
        double budgetedAmount = budgetRepository.getTotalBudgetInCategoryForUser(userId,categoryId);
        // Save the new budget
        budget =  budgetRepository.save(budget);

        if (totalExpensesInCategory == 0) {
            budget.setSpendingPercentage(0.0); // Avoid division by zero
        } else {
            double spendingPercentage = ( totalExpensesInCategory/budgetedAmount) * 100;
            budget.setSpendingPercentage(spendingPercentage);
        }
        budget =  budgetRepository.save(budget);
        return budget;
    }

    public Budget updateBudget(int budgetId,int userId, int categoryId, Budget updatedBudget) {
        Budget existingBudget = getBudgetById(budgetId);
        if (existingBudget != null) {
            updatedBudget.setBudgetId(budgetId);
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                // Handle user not found
                return null;
            }
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category == null) {
                // Handle category not found
                return null;
            }
            updatedBudget.setUser(user);
            updatedBudget.setCategory(category);
            updatedBudget =  budgetRepository.save(updatedBudget);

            // Calculate the total budget for the user and update it
//            double totalBudget = budgetRepository.calculateTotalBudgetForUser(userId);
//            user.setTotalExpenses(totalBudget);
//            userRepository.save(user);

            // Calculate Spending Percentage
            double totalExpensesInCategory = expenseRepository.getTotalExpensesInCategoryForUser(userId, categoryId);
            updatedBudget.setTotalExpensesInCategory(totalExpensesInCategory);
            double budgetedAmount = budgetRepository.getTotalBudgetInCategoryForUser(userId,categoryId);
            // Save the new budget
            updatedBudget =  budgetRepository.save(updatedBudget);

            if (totalExpensesInCategory == 0) {
                updatedBudget.setSpendingPercentage(0.0); // Avoid division by zero
            } else {
                double spendingPercentage = ( totalExpensesInCategory/budgetedAmount) * 100;
                updatedBudget.setSpendingPercentage(spendingPercentage);
            }
            updatedBudget =  budgetRepository.save(updatedBudget);
            return updatedBudget;
        }
        return null;
    }

    public void deleteBudget(int budgetId) {
        budgetRepository.deleteById(budgetId);
    }
}
