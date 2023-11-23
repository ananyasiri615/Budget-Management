package com.bcc.service;

import com.bcc.entity.Investment;
import com.bcc.entity.User;
import com.bcc.repository.InvestmentRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    public Investment calculateFutureValue(int userId, Investment investment) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }

        investment.setUser(user);

        double initialInvestment = investment.getInitialInvestment();
        double annualInterestRate = investment.getAnnualInterestRate();
        int compoundingFrequency = investment.getCompoundingFrequency();
        int investmentDuration = investment.getInvestmentDuration();

        double ratePerPeriod = (annualInterestRate / 12)/100;
        int periods = compoundingFrequency * investmentDuration;

        double futureValue =  initialInvestment * Math.pow(1 + (ratePerPeriod/compoundingFrequency), periods);
        investment.setFutureValue(futureValue);
//        totalInvesmentForUser(userId,investment);
        return investmentRepository.save(investment);
    }
    private double totalInvesmentForUser(int userId, Investment investment){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return 0;
        }
        investment.setUser(user);
        double totalInvestmentForUser = investmentRepository.calculateTotalInvestmentForUser(userId);
        user.setTotalInvestment(totalInvestmentForUser);
        return totalInvestmentForUser;
    }
    // Read an Investment by its ID
    public Investment getInvestmentById(int investmentId) {
        return investmentRepository.findById(investmentId).orElse(null);
    }

    // Update an Investment
    public Investment updateInvestment(int userId, int investmentId, Investment updatedInvestment) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        Investment existingInvestment = investmentRepository.findById(investmentId).orElse(null);
        if (existingInvestment == null) {
            // Handle Investment not found
            return null;
        }

        // Validate that the updatedInvestment belongs to the same user as the existingInvestment
        int existingUserId = existingInvestment.getUser().getUserId();
        int updatedUserId = updatedInvestment.getUser().getUserId();
        if (existingUserId != updatedUserId) {
            // Handle unauthorized update
            return null;
        }

        // Update the fields as needed
        existingInvestment.setInitialInvestment(updatedInvestment.getInitialInvestment());
        existingInvestment.setAnnualInterestRate(updatedInvestment.getAnnualInterestRate());
        existingInvestment.setCompoundingFrequency(updatedInvestment.getCompoundingFrequency());
        existingInvestment.setInvestmentDuration(updatedInvestment.getInvestmentDuration());
//        totalInvesmentForUser(userId,updatedInvestment);

        return investmentRepository.save(existingInvestment);
    }

    // Delete an Investment
    public boolean deleteInvestment(int investmentId) {
        Investment investment = investmentRepository.findById(investmentId).orElse(null);
        if (investment == null) {
            // Handle Investment not found
            return false;
        }

        investmentRepository.delete(investment);
        return true;
    }

}
