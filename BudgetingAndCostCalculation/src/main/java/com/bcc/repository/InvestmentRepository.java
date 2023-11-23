package com.bcc.repository;

import com.bcc.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    @Query("SELECT SUM(i.futureValue) FROM Investment i WHERE i.user.userId = :userId")
    Double calculateTotalInvestmentForUser(@Param("userId") int userId);
}
