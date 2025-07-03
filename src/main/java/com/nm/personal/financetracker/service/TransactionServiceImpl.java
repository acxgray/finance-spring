package com.nm.personal.financetracker.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nm.personal.financetracker.dto.TransactionDto;
import com.nm.personal.financetracker.model.Billing;
import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.model.Transaction;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.BillingRepository;
import com.nm.personal.financetracker.repository.CategoryRepository;
import com.nm.personal.financetracker.repository.TransactionRepository;
import com.nm.personal.financetracker.repository.UserRepository;
import com.nm.personal.financetracker.utils.EntityDtoMapper;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BillingRepository billingRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, BillingRepository billingRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.billingRepository = billingRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getAllTransactions() {
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllTransactionsV2() {
        List<TransactionDto> transactions = transactionRepository.findAll().stream().map(this::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(transactions,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransactionById(Long id) {
        Transaction transactionDetail = transactionRepository.findById(id).orElse(null);

        if (transactionDetail == null) {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(EntityDtoMapper.toDto(transactionDetail), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveTransaction(TransactionDto transaction) {
        Transaction newData = new Transaction();
        
        newData.setNote(transaction.getNote());
        newData.setAmount(transaction.getAmount());
        newData.setStatus(transaction.getStatus());

        User user = userRepository.findById(transaction.getUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        newData.setUser(user);

        Category category = categoryRepository.findById(transaction.getCategory().getId()).orElseThrow(() -> new RuntimeException("Category not found"));
        newData.setCategory(category);

        // Create Billing
        if (transaction.getCategory().getId().equals(Long.valueOf(1))) {
            Billing billing = new Billing();
            billing.setTransaction(newData);
            billing.setCreated_at(LocalDateTime.now());
            billingRepository.save(billing);
        }

        return new ResponseEntity<>(transactionRepository.save(newData), HttpStatus.CREATED);
    }

    @Override
    @Transactional
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
    @Transactional
    public ResponseEntity<?> deleteTransaction(Long id) {
        Transaction transactionDetail = transactionRepository.findById(id).orElse(null);

        if (transactionDetail == null) {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        }

        transactionRepository.deleteById(id);

        return new ResponseEntity<>("Transaction has been removed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchDashboard(Long id) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total_transaction_amount", transactionRepository.getTotalTransactionByUser(id));
        stats.put("total_transaction_income", transactionRepository.getTotalIncomeByUser(id));
        stats.put("total_transaction_expenses", transactionRepository.getTotalExpensesByUser(id));

        // No of Transactions per month
        // Map<String, Object> noOfTransactions = new HashMap<>();
        stats.put("total_transactions", transactionRepository.getTotalTransactionsByUserAndByDate(id));
        

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    public TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setNote(transaction.getNote());
        dto.setStatus(transaction.getStatus());
        dto.setCreated_at(transaction.getCreated_at());
        dto.setUpdated_at(transaction.getUpdated_at());
        dto.setUser(EntityDtoMapper.userToDto(transaction.getUser()));
        dto.setCategory(EntityDtoMapper.categoryToDto(transaction.getCategory()));

        return dto;
    }

}
