package com.bcc.repository;

import com.bcc.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    @Query("SELECT b FROM Budget b WHERE b.user.userId = :userId AND b.category.categoryId = :categoryId")
    Budget findBudgetByUserAndCategory(@Param("userId") int userId, @Param("categoryId") int categoryId);

    @Query("SELECT SUM(b.budgetedAmount) FROM Budget b WHERE b.user.userId = :userId")
    Double calculateTotalBudgetForUser(@Param("userId") int userId);

    @Query("SELECT SUM(b.budgetedAmount) FROM Budget b WHERE b.user.userId = :userId AND b.category.categoryId = :categoryId")
    Double getTotalBudgetInCategoryForUser(@Param("userId") int userId, @Param("categoryId") int categoryId);


}

