package com.nm.personal.financetracker.utils;

import org.springframework.stereotype.Component;

import com.nm.personal.financetracker.dto.CategoryDto;
import com.nm.personal.financetracker.dto.TransactionDto;
import com.nm.personal.financetracker.dto.UserDto;
import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.model.Transaction;
import com.nm.personal.financetracker.model.User;

@Component
public class EntityDtoMapper {
    
    public static UserDto userToDto(User user) {
        UserDto userResponseDto = new UserDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirst_name(user.getFirst_name());
        userResponseDto.setLast_name(user.getLast_name());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }

    public static CategoryDto categoryToDto(Category category) {
        CategoryDto categoryResponseDto = new CategoryDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setTitle(category.getTitle());
        categoryResponseDto.setNote(category.getNote());
        categoryResponseDto.setTransaction_type(category.getTransaction_type());

        return categoryResponseDto;
    }
    
    public static TransactionDto toDto(Transaction transaction) {
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
