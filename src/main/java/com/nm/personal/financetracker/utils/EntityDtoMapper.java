package com.nm.personal.financetracker.utils;

import org.springframework.stereotype.Component;

import com.nm.personal.financetracker.dto.CategoryResponseDto;
import com.nm.personal.financetracker.dto.TransactionResponseDto;
import com.nm.personal.financetracker.dto.UserResponseDto;
import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.model.Transaction;
import com.nm.personal.financetracker.model.User;

@Component
public class EntityDtoMapper {
    
    public static UserResponseDto userToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirst_name(user.getFirst_name());
        userResponseDto.setLast_name(user.getLast_name());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    public static CategoryResponseDto categoryToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setTitle(category.getTitle());
        categoryResponseDto.setNote(category.getNote());
        categoryResponseDto.setTransaction_type(category.getTransaction_type());

        return categoryResponseDto;
    }
    
    public static TransactionResponseDto toDto(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
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
