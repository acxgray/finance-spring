package com.nm.personal.financetracker.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.model.Billing;

public interface BillingService {

    ResponseEntity<?> getAllBilling();

    ResponseEntity<?> saveBilling(Billing billing);

    ResponseEntity<?> getBillById(UUID id);

    ResponseEntity<?> updateBillingById(UUID id, Billing billing);

    ResponseEntity<?> deleteBillingById(UUID id);
}
