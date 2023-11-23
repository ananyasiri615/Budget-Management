package com.bcc.controller;

import com.bcc.entity.Savings;
import com.bcc.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/savings")
public class SavingsController {
    @Autowired
    private SavingsService savingsService;

    @GetMapping("/all")
    public List<Savings> getAllSavings() {
        return savingsService.getAllSavings();
    }

    @GetMapping("/id/{savingsId}")
    public ResponseEntity<Savings> getSavingsById(@PathVariable int savingsId) {
        Savings savings = savingsService.getSavingsById(savingsId);
        if (savings != null) {
            return ResponseEntity.ok(savings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/forUser/{userId}/create")
    public ResponseEntity<Savings> createSavings(@PathVariable int userId, @RequestBody Savings savings) {
        Savings createdSavings = savingsService.createSavings(userId, savings);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSavings);
    }

    @PutMapping("/forUser/{userId}/update/{savingsId}")
    public ResponseEntity<Savings> updateSavings(@PathVariable int savingsId, @PathVariable int userId, @RequestBody Savings updatedSavings) {
        Savings savings = savingsService.updateSavings(savingsId, userId, updatedSavings);
        if (savings != null) {
            return ResponseEntity.ok(savings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

