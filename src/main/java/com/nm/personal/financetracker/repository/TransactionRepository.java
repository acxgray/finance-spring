package com.nm.personal.financetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nm.personal.financetracker.dto.MonthlyTransaction;
import com.nm.personal.financetracker.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query(nativeQuery = true, value = "SELECT SUM(amount) AS total_transaction_amount FROM transaction WHERE user_id = ?1")
    Double getTotalTransactionByUser(Long id);

    @Query(nativeQuery = true, value = "SELECT SUM(amount) AS total_transaction_amount FROM transaction WHERE user_id = ?1 AND category_id = 2")
    Double getTotalIncomeByUser(Long id);

    @Query(nativeQuery = true, value = "SELECT SUM(amount) AS total_transaction_amount FROM transaction WHERE user_id = ?1 AND category_id = 1")
    Double getTotalExpensesByUser(Long id);

    @Query(nativeQuery = true, value = "select DATE_FORMAT(created_at, '%Y-%m') as month, count(*) as total from transaction WHERE user_id = ?1 GROUP BY month ORDER BY month ASC ")
    List<MonthlyTransaction> getTotalTransactionsByUserAndByDate(Long id);
}
