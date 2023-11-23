package com.bcc.controller;

import com.bcc.entity.Investment;
import com.bcc.entity.User;
import com.bcc.repository.InvestmentRepository;
import com.bcc.repository.UserRepository;
import com.bcc.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/investment")
public class InvestmentController {

    private final InvestmentService investmentService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvestmentRepository investmentRepository;


    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping("/ForUser/{userId}/create")
    public Investment calculateFutureValue(@PathVariable int userId, @RequestBody Investment investment) {
        return investmentService.calculateFutureValue(userId, investment);
    }

    @GetMapping("/id/{investmentId}")
    public ResponseEntity<Investment> getInvestment(@PathVariable int investmentId) {
        Investment investment = investmentService.getInvestmentById(investmentId);
        if (investment == null) {
            // Handle Investment not found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(investment);
    }

    @PutMapping("/ForUser/{userId}/update/{investmentId}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable int userId, @PathVariable int investmentId, @RequestBody Investment updatedInvestment) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double totalInvestment = user.getTotalInvestment() - updatedInvestment.getFutureValue();
        user.setTotalInvestment(totalInvestment);
        userRepository.save(user);
        Investment updated = investmentService.updateInvestment(userId, investmentId, updatedInvestment);
        if (updated == null) {
            // Handle Investment not found or unauthorized update
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/ForUser/{userId}/delete/{investmentId}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable int investmentId, @PathVariable int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        Investment investment = investmentRepository.findById(investmentId).orElse(null);
        double totalInvestment = user.getTotalInvestment() - investment.getFutureValue();
        user.setTotalInvestment(totalInvestment);
        userRepository.save(user);
        if (investmentService.deleteInvestment(investmentId)) {
            return ResponseEntity.noContent().build();
        } else {
            // Handle Investment not found
            return ResponseEntity.notFound().build();
        }
    }
}
