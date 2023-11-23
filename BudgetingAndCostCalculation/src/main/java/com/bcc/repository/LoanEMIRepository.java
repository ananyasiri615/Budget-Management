package com.bcc.repository;

import com.bcc.entity.LoanEMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanEMIRepository extends JpaRepository<LoanEMI, Integer> {
    // Define a custom query to calculate the total loan EMI for a specific userId
    @Query("SELECT SUM(le.emi) FROM LoanEMI le WHERE le.user.userId = :userId")
    Double calculateTotalLoanEMIForUser(@Param("userId") int userId);

}
