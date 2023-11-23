package com.bcc.controller;

import com.bcc.entity.Budget;
import com.bcc.entity.User;
import com.bcc.repository.BudgetRepository;
import com.bcc.repository.UserRepository;
import com.bcc.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetRepository budgetRepository;
    @GetMapping("/all")
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/id/{budgetId}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable int budgetId) {
        Budget budget = budgetService.getBudgetById(budgetId);
        if (budget != null) {
            return ResponseEntity.ok(budget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/forUser/{userId}/withCategory/{categoryId}/create")
    public ResponseEntity<Budget> createBudget(@PathVariable int userId, @PathVariable int categoryId,@RequestBody Budget budget) {
        Budget createdBudget = budgetService.createBudget(userId,categoryId,budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBudget);
    }

    @PutMapping("/forUser/{userId}/withCategory/{categoryId}/update/{budgetId}")
    public ResponseEntity<Budget> updateBudget(@PathVariable int budgetId,@PathVariable int userId, @PathVariable int categoryId, @RequestBody Budget updatedBudget) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double totalBudget = user.getTotalBudget() - updatedBudget.getBudgetedAmount();
        user.setTotalBudget(totalBudget);
        userRepository.save(user);
        Budget budget = budgetService.updateBudget(budgetId,userId,categoryId, updatedBudget);
        if (budget != null) {
            return ResponseEntity.ok(budget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/forUser/{userId}/withCategory/{categoryId}/delete/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable int userId, @PathVariable int categoryId,@PathVariable int budgetId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        Budget budget = budgetRepository.findBudgetByUserAndCategory(userId, categoryId);
        double totalBudget = user.getTotalBudget() - budget.getBudgetedAmount();
        user.setTotalBudget(totalBudget);
        userRepository.save(user);
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}

