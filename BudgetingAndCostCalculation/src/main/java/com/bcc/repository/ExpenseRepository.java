package com.bcc.repository;

import com.bcc.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT SUM(e.totalExpensesInCategory) FROM Expense e WHERE e.user.userId = :userId")
    Double calculateTotalExpensesForUser(@Param("userId") int userId);

    @Query("SELECT e FROM Expense e WHERE e.user.userId = :userId AND e.category.categoryId = :categoryId")
    Expense findExpenseByUserAndCategory(@Param("userId") int userId, @Param("categoryId") int categoryId);

    @Query("SELECT SUM(e.totalExpensesInCategory) FROM Expense e WHERE e.user.userId = :userId AND e.category.categoryId = :categoryId")
    Double getTotalExpensesInCategoryForUser(@Param("userId") int userId, @Param("categoryId") int categoryId);



}
