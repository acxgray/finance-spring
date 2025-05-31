package com.nm.personal.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nm.personal.financetracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
