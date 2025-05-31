package com.nm.personal.financetracker.service;

import org.springframework.http.ResponseEntity;

import com.nm.personal.financetracker.model.User;

public interface UserService {
    
    // Get All Users
    ResponseEntity<?> getAllUsers();

    // GET: get by id
    ResponseEntity<?> getUserById(Long id);

    // POST
    ResponseEntity<?> saveUser(User user);

    // PUT
    ResponseEntity<?> updateUser(Long id, User user);

    // DELETE
    ResponseEntity<?> deleteUser(Long id);

}
