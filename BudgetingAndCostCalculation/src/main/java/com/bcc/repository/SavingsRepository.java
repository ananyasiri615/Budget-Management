package com.bcc.repository;

import com.bcc.entity.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsRepository extends JpaRepository<Savings, Integer> {
}
