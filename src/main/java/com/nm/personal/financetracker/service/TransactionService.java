package com.nm.personal.financetracker.service;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.dto.TransactionDto;
import com.nm.personal.financetracker.model.Transaction;

public interface TransactionService {

    ResponseEntity<?> getAllTransactions();

    ResponseEntity<?> getAllTransactionsV2();

    ResponseEntity<?> getTransactionById(Long id);

    ResponseEntity<?> saveTransaction(TransactionDto transaction);

    ResponseEntity<?> updateTransaction(Long id, Transaction transaction);

    ResponseEntity<?> deleteTransaction(Long id);
    
}
