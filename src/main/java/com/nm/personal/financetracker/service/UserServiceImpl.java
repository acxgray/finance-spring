package com.nm.personal.financetracker.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        User userDetail = userRepository.findById(id).orElse(null);

        if (userDetail == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveUser(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        User userDetail = userRepository.findById(id).orElse(null);

        if (userDetail != null) {
            userDetail.setFirst_name(user.getFirst_name());
            userDetail.setLast_name(user.getLast_name());
            userDetail.setEmail(user.getEmail());
            userDetail.setGender(user.getGender());
            userDetail.setUpdated_at(LocalDateTime.now());
            
            return new ResponseEntity<>(userRepository.save(userDetail), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        User userDetail = userRepository.findById(id).orElse(null);

        if (userDetail == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);

        return new ResponseEntity<>("User has been removed", HttpStatus.OK);
    }
}
