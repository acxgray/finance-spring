package com.nm.personal.financetracker.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Billing {

    public Billing() {
    }

    

    public Billing(Transaction transaction, LocalDateTime created_at) {
        this.transaction = transaction;
        this.created_at = created_at;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(nullable = true)
    private double paid_amount;

    @Column(nullable = true)
    private LocalDateTime paid_date;

    @Column(nullable = true)
    private String paid_method;

    @Column(nullable = true)
    private String paid_ref;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updated_at;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
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
