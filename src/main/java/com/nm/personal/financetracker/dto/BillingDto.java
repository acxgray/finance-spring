package com.nm.personal.financetracker.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BillingDto {

    private UUID id;

    private TransactionDto transaction;

    private double paid_amount;

    private LocalDateTime paid_date;
    
    private String paid_method;

    private String paid_ref;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TransactionDto getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDto transaction) {
        this.transaction = transaction;
    }

    public double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public LocalDateTime getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(LocalDateTime paid_date) {
        this.paid_date = paid_date;
    }

    public String getPaid_method() {
        return paid_method;
    }

    public void setPaid_method(String paid_method) {
        this.paid_method = paid_method;
    }

    public String getPaid_ref() {
        return paid_ref;
    }

    public void setPaid_ref(String paid_ref) {
        this.paid_ref = paid_ref;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }    
}
