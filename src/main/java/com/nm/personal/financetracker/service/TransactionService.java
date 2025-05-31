package com.nm.personal.financetracker.service;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.model.Transaction;

public interface TransactionService {

    ResponseEntity<?> getAllTransactions();

    ResponseEntity<?> getTransactionById(Long id);

    ResponseEntity<?> saveTransaction(Transaction transaction);

    ResponseEntity<?> updateTransaction(Long id, Transaction transaction);

    ResponseEntity<?> deleteTransaction(Long id);
    
}
