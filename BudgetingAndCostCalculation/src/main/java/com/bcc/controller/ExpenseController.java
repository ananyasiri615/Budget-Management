package com.bcc.controller;

import com.bcc.entity.Expense;
import com.bcc.entity.User;
import com.bcc.repository.ExpenseRepository;
import com.bcc.repository.UserRepository;
import com.bcc.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/all")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/id/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/forUser/{userId}/withCategory/{categoryId}/create")
    public ResponseEntity<Expense> createExpense(@PathVariable int userId, @PathVariable int categoryId, @RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(userId,categoryId,expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @PutMapping("/forUser/{userId}/withCategory/{categoryId}/update/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable int expenseId, @PathVariable int userId, @PathVariable int categoryId, @RequestBody Expense updatedExpense) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double totalExpenses = user.getTotalExpenses() - updatedExpense.getTotalExpensesInCategory();
        user.setTotalExpenses(totalExpenses);
        userRepository.save(user);
        Expense expense = expenseService.updateExpense(expenseId,userId,categoryId, updatedExpense);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/forUser/{userId}/withCategory/{categoryId}/delete/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable int userId, @PathVariable int categoryId, @PathVariable int expenseId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        Expense expense = expenseRepository.findExpenseByUserAndCategory(userId, categoryId);
        double totalExpenses = user.getTotalExpenses() - expense.getTotalExpensesInCategory();
        user.setTotalExpenses(totalExpenses);
        userRepository.save(user);
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }
}

