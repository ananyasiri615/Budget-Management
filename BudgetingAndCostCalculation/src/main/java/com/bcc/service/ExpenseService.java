package com.bcc.service;

import com.bcc.entity.Category;
import com.bcc.entity.Expense;
import com.bcc.entity.User;
import com.bcc.repository.CategoryRepository;
import com.bcc.repository.ExpenseRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(int expenseId) {
        return expenseRepository.findById(expenseId).orElse(null);
    }

public Expense createExpense(int userId, int categoryId, Expense expense) {
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
    Expense existingExpense = expenseRepository.findExpenseByUserAndCategory(userId, categoryId);
    if (existingExpense != null) {
        // An expense for the same category already exists
        // Suggest updating the existing expense
        return existingExpense;
    }
    expense.setUser(user);
    expense.setCategory(category);
    expense = expenseRepository.save(expense);

    // Calculate the total expenses for the user and update it
    double totalExpenses = expenseRepository.calculateTotalExpensesForUser(userId);
    user.setTotalExpenses(totalExpenses);
    userRepository.save(user);

    // Calculate Spending Percentage
    double totalExpensesInCategory = expenseRepository.getTotalExpensesInCategoryForUser(userId, categoryId); // Assuming you have this property in the Category entity
    totalExpenses = user.getTotalExpenses();

    if (totalExpenses == 0) {
        expense.setExpendingPercentage(0.0); // Avoid division by zero
    } else {
        double spendingPercentage = (totalExpensesInCategory / totalExpenses) * 100;
        expense.setExpendingPercentage(spendingPercentage);
    }

    // Save the new expense
    expense = expenseRepository.save(expense);


    return expense;
}

    public Expense updateExpense(int expenseId, int userId, int categoryId, Expense updatedExpense) {
        Expense existingExpense = getExpenseById(expenseId);
        if (existingExpense != null) {
            updatedExpense.setExpenseId(expenseId);
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
            updatedExpense.setUser(user);
            updatedExpense.setCategory(category);
            double totalExpenses = expenseRepository.calculateTotalExpensesForUser(userId);
            user.setTotalExpenses(totalExpenses);
            userRepository.save(user);
            updatedExpense = expenseRepository.save(updatedExpense);

            // Calculate Spending Percentage
            double totalExpensesInCategory = expenseRepository.getTotalExpensesInCategoryForUser(userId, categoryId); // Assuming you have this property in the Category entity
            totalExpenses = user.getTotalExpenses();

            if (totalExpenses == 0) {
                updatedExpense.setExpendingPercentage(0.0); // Avoid division by zero
            } else {
                double spendingPercentage = (totalExpensesInCategory / totalExpenses) * 100;
                updatedExpense.setExpendingPercentage(spendingPercentage);
            }

            // Save the new expense
            updatedExpense = expenseRepository.save(updatedExpense);


            return updatedExpense;
        }
        return null;
    }

    public void deleteExpense(int expenseId) {
        expenseRepository.deleteById(expenseId);
    }

}
