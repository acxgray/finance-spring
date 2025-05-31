package com.nm.personal.financetracker;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nm.personal.financetracker.model.Category;
import com.nm.personal.financetracker.model.TransactionType;
import com.nm.personal.financetracker.model.User;
import com.nm.personal.financetracker.repository.CategoryRepository;
import com.nm.personal.financetracker.repository.UserRepository;

@SpringBootApplication
public class FinancetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancetrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertData(UserRepository userRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			// Create User
			userRepository.save(new User("Ned", "Mostoles", "nedmostoles@gmail.com", "Male", LocalDateTime.now()));

			// Category
			categoryRepository.save(new Category("Bills", "Category for bills", TransactionType.EXPENSE));
			categoryRepository.save(new Category("Salary", "Category for bills", TransactionType.INCOME));
		};
	}

}
