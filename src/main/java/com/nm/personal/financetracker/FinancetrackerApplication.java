package com.nm.personal.financetracker;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nm.personal.financetracker.model.Billing;
import com.nm.personal.financetracker.model.BillingStatus;
import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.model.Transaction;
import com.nm.personal.financetracker.model.TransactionType;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.BillingRepository;
import com.nm.personal.financetracker.repository.CategoryRepository;
import com.nm.personal.financetracker.repository.TransactionRepository;
import com.nm.personal.financetracker.repository.UserRepository;

@SpringBootApplication
public class FinancetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancetrackerApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner insertData(UserRepository userRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository, BillingRepository billingRepository) {
		return (args) -> {
			// Create User
			userRepository.save(new User("Ned", "Mostoles", "nedmostoles@gmail.com", "user", passwordEncoder.encode("password"), "ADMIN", "Male",
					LocalDateTime.now()));

			// Category
			categoryRepository.save(new Category("Bills", "Category for Bills", TransactionType.EXPENSE));
			categoryRepository.save(new Category("Salary", "Category for Salary", TransactionType.INCOME));

			// User
			User userDetail = userRepository.findById(Long.valueOf(1)).orElseThrow(() -> new RuntimeException());
			Category categoryDetail = categoryRepository.findById(Long.valueOf(1)).orElseThrow(() -> new RuntimeException());

			// Transaction
			transactionRepository.save(new Transaction(userDetail, categoryDetail, 2500.00, BillingStatus.UNPAID, "Bills", LocalDateTime.now()));

			// Bill
			Transaction transactionDetail = transactionRepository.findById(Long.valueOf(1)).orElseThrow(() -> new RuntimeException());

			billingRepository.save(new Billing(transactionDetail, LocalDateTime.now()));
			
		};
	}

}
