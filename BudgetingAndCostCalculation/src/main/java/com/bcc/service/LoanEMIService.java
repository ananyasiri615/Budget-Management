package com.bcc.service;

import com.bcc.entity.LoanEMI;
import com.bcc.entity.User;
import com.bcc.repository.LoanEMIRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanEMIService {

    private final LoanEMIRepository loanEMIRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanEMIService(LoanEMIRepository loanEMIRepository, UserRepository userRepository) {
        this.loanEMIRepository = loanEMIRepository;
        this.userRepository = userRepository;
    }

    public LoanEMI calculateLoanEMI(int userId, LoanEMI loanEMI) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }

        loanEMI.setUser(user);
        double principal = loanEMI.getPrincipal();
        double annualInterestRate = loanEMI.getAnnualInterestRate();
        int numberOfMonths = loanEMI.getNumberOfMonths();

        // Calculate the monthly interest rate
        double monthlyInterestRate = (annualInterestRate / 12) / 100;

        // Calculate EMI using the formula
        double emi = principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonths)
                / (Math.pow(1 + monthlyInterestRate, numberOfMonths) - 1);

        loanEMI.setEmi(emi);

//        totalEmiForUser(userId,loanEMI);
        return loanEMIRepository.save(loanEMI);
    }

    private double totalEmiForUser(int userId, LoanEMI loanEMI){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return 0;
        }
        loanEMI.setUser(user);
        Double totalEmiForUser = loanEMIRepository.calculateTotalLoanEMIForUser(userId);
        if (totalEmiForUser != null) {
            user.setTotalEmi(totalEmiForUser);
        } else {
            user.setTotalEmi(0.0);
        }
        return totalEmiForUser;
    }
    // Read a LoanEMI by its ID
    public LoanEMI getLoanEMIById(int loanId) {
        return loanEMIRepository.findById(loanId).orElse(null);
    }

    // Update a LoanEMI
    public LoanEMI updateLoanEMI(int userId, int loanId, LoanEMI updatedLoanEMI) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        LoanEMI existingLoanEMI = loanEMIRepository.findById(loanId).orElse(null);
        if (existingLoanEMI == null) {
            // Handle LoanEMI not found
            return null;
        }

        // Validate that the updatedLoanEMI belongs to the same user as the existingLoanEMI
        int existingUserId = existingLoanEMI.getUser().getUserId();
        int updatedUserId = updatedLoanEMI.getUser().getUserId();
        if (existingUserId != updatedUserId) {
            // Handle unauthorized update
            return null;
        }

        // Update the fields as needed
        existingLoanEMI.setPrincipal(updatedLoanEMI.getPrincipal());
        existingLoanEMI.setAnnualInterestRate(updatedLoanEMI.getAnnualInterestRate());
        existingLoanEMI.setNumberOfMonths(updatedLoanEMI.getNumberOfMonths());
//        totalEmiForUser(userId,updatedLoanEMI);

        return loanEMIRepository.save(existingLoanEMI);
    }

    // Delete a LoanEMI
    public boolean deleteLoanEMI(int loanId) {
        LoanEMI loanEMI = loanEMIRepository.findById(loanId).orElse(null);
        if (loanEMI == null) {
            // Handle LoanEMI not found
            return false;
        }

        loanEMIRepository.delete(loanEMI);
        return true;
    }
}
