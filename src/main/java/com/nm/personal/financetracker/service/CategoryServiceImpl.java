package com.nm.personal.financetracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nm.personal.financetracker.dto.CategoryResponseDto;
import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Override
    public ResponseEntity<?> getAllCategories() {
        List<CategoryResponseDto> categories = categoryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    private CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setNote(category.getNote());
        dto.setTransaction_type(category.getTransaction_type());
        return dto;
    }
}
