package com.nm.personal.financetracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.personal.financetracker.model.Billing;

@Repository
public interface  BillingRepository extends JpaRepository<Billing, UUID> {
    
}
