package com.nm.personal.financetracker.dto;

import java.time.LocalDateTime;

import com.nm.personal.financetracker.model.BillingStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionDto {

    private Long id;

    @Valid
    @NotNull( message = "User is required")
    private UserDto user;

    @Valid
    @NotNull( message = "Category is required")
    private CategoryDto category;

    @NotNull(message = "Transaction Amount is Required")
    private Double amount;

    @NotBlank(message = "Transaction Note is Required")
    private String note;

    @NotNull(message = "Status is Required")
    private BillingStatus status;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BillingStatus getStatus() {
        return status;
    }

    public void setStatus(BillingStatus status) {
        this.status = status;
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
