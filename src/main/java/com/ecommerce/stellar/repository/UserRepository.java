package com.ecommerce.stellar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.stellar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);

	boolean existsByEmailAndUserIdNot(String email, Long userId);
}
