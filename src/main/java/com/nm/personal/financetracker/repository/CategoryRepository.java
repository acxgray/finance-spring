package com.nm.personal.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.personal.financetracker.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
