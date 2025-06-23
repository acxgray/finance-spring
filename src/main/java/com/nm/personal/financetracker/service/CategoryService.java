package com.nm.personal.financetracker.service;

import org.springframework.http.ResponseEntity;

public interface CategoryService {
    
    ResponseEntity<?> getAllCategories();
}
