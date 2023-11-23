package com.bcc.service;

import com.bcc.entity.Savings;
import com.bcc.entity.User;
import com.bcc.repository.SavingsRepository;
import com.bcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingsService {
    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Savings> getAllSavings() {
        return savingsRepository.findAll();
    }

    public Savings getSavingsById(int savingsId) {
        return savingsRepository.findById(savingsId).orElse(null);
    }

    public Savings createSavings(int userId, Savings savings) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            // Handle user not found
            return null;
        }
        double netSavings = user.getTotalFixedIncome()-(user.getTotalExpenses());
        user.setNetSavings(netSavings);
        savings.setNetSavings(netSavings);
        return savingsRepository.save(savings);
    }

    public Savings updateSavings(int savingsId, int userId, Savings updatedSavings) {
        Savings existingSavings = getSavingsById(savingsId);
        if (existingSavings != null) {
            updatedSavings.setSavingsId(savingsId);
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                // Handle user not found
                return null;
            }
            double netSavings = user.getTotalFixedIncome()- (user.getTotalExpenses());
            user.setNetSavings(netSavings);
            existingSavings.setNetSavings(netSavings);
            return savingsRepository.save(updatedSavings);
        }
        return null;
    }

}

