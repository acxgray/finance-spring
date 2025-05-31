package com.nm.personal.financetracker.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nm.personal.financetracker.model.Transaction;
import com.nm.personal.financetracker.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<?> getAllTransactions() {
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransactionById(Long id) {
        Transaction transactionDetail = transactionRepository.findById(id).orElse(null);

        if (transactionDetail == null) {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transactionDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveTransaction(Transaction transaction) {
        return new ResponseEntity<>(transactionRepository.save(transaction), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateTransaction(Long id, Transaction updateTransaction) {
        Transaction transactionDetail = transactionRepository.findById(id).orElse(null);

        if (transactionDetail != null) {

            transactionDetail.getCategory().setTitle(updateTransaction.getCategory().getTitle());
            transactionDetail.getCategory().setNote(updateTransaction.getCategory().getNote());
            transactionDetail.getCategory().setTransaction_type(updateTransaction.getCategory().getTransaction_type());
            transactionDetail.setAmount(updateTransaction.getAmount());
            transactionDetail.setNote(updateTransaction.getNote());
            transactionDetail.setUpdated_at(LocalDateTime.now());

            return new ResponseEntity<>(transactionRepository.save(transactionDetail), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteTransaction(Long id) {
        Transaction transactionDetail = transactionRepository.findById(id).orElse(null);

        if (transactionDetail == null) {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }

        transactionRepository.deleteById(id);

        return new ResponseEntity<>("Transaction has been removed", HttpStatus.OK);
    }

}
