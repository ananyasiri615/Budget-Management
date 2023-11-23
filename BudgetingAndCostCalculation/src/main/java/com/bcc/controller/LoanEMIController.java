package com.bcc.controller;

import com.bcc.entity.LoanEMI;
import com.bcc.entity.User;
import com.bcc.repository.LoanEMIRepository;
import com.bcc.repository.UserRepository;
import com.bcc.service.LoanEMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/loan-emi")
public class LoanEMIController {

    private final LoanEMIService loanEMIService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanEMIRepository loanEMIRepository;

    @Autowired
    public LoanEMIController(LoanEMIService loanEMIService) {
        this.loanEMIService = loanEMIService;
    }

    @PostMapping("/ForUser/{userId}/create")
    public LoanEMI calculateLoanEMI(@PathVariable int userId, @RequestBody LoanEMI loanEMI) {
        return loanEMIService.calculateLoanEMI(userId, loanEMI);
    }

    @GetMapping("/id/{loanId}")
    public ResponseEntity<LoanEMI> getLoanEMI(@PathVariable int loanId) {
        LoanEMI loanEMI = loanEMIService.getLoanEMIById(loanId);
        if (loanEMI == null) {
            // Handle LoanEMI not found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loanEMI);
    }

    @PutMapping("/ForUser/{userId}/update/{loanId}")
    public ResponseEntity<LoanEMI> updateLoanEMI(@PathVariable int userId, @PathVariable int loanId, @RequestBody LoanEMI updatedLoanEMI) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double totalLoan = user.getTotalEmi() - updatedLoanEMI.getEmi();
        user.setTotalEmi(totalLoan);
        userRepository.save(user);
        LoanEMI loanEMI = loanEMIService.updateLoanEMI(userId, loanId, updatedLoanEMI);
        if (loanEMI == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loanEMI);
    }

    @DeleteMapping("/ForUser/{userId}/delete/{loanId}")
    public ResponseEntity<Void> deleteLoanEMI(@PathVariable int userId, @PathVariable int loanId) {
        LoanEMI LoanEMI = loanEMIRepository.findById(loanId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double totalLoan = user.getTotalEmi() - LoanEMI.getEmi();
        user.setTotalEmi(totalLoan);
        userRepository.save(user);
        if (loanEMIService.deleteLoanEMI(loanId)) {
            return ResponseEntity.noContent().build();
        } else {
            // Handle LoanEMI not found
            return ResponseEntity.notFound().build();
        }
    }
}

