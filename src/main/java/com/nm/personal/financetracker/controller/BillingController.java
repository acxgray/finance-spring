package com.nm.personal.financetracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nm.personal.financetracker.service.BillingService;

@RestController
@RequestMapping("/api/v1/bills")
public class BillingController {
    
    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<?> getAllBilling() {
        return billingService.getAllBilling();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable UUID id) {
        return billingService.getBillById(id);
    }

}
