package com.nm.personal.financetracker.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nm.personal.financetracker.model.Billing;
import com.nm.personal.financetracker.repository.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public ResponseEntity<?> getAllBilling() {
        return new ResponseEntity<>(billingRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveBilling(Billing billing) {
        return new ResponseEntity<>(billingRepository.save(billing), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getBillById(UUID id) {
        Billing getBilling = billingRepository.findById(id).orElse(null);
        if (getBilling == null) {
            return new ResponseEntity<>("Bill Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getBilling, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBillingById(UUID id, Billing billing) {
        Billing getBilling = billingRepository.findById(id).orElse(null);
        if (getBilling == null) {
            return new ResponseEntity<>("Bill Not Found", HttpStatus.NOT_FOUND);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> deleteBillingById(UUID id) {
        Billing getBilling = billingRepository.findById(id).orElse(null);
        if (getBilling == null) {
            return new ResponseEntity<>("Bill Not Found", HttpStatus.NOT_FOUND);
        }

        billingRepository.deleteById(id);
        return new ResponseEntity<>("Billing has been removed", HttpStatus.OK);
    }
}
