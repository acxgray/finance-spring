package com.nm.personal.financetracker.dto;

import com.nm.personal.financetracker.model.TransactionType;

import jakarta.validation.constraints.NotNull;

public class CategoryDto {

    @NotNull(message = "Please Select the Category")
    private Long id;

    private String title;

    private String note;
    
    private TransactionType transaction_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TransactionType getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(TransactionType transaction_type) {
        this.transaction_type = transaction_type;
    }
}
