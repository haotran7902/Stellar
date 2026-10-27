package com.ecommerce.stellar.service.impl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.stellar.dto.request.UserCreateRequest;
import com.ecommerce.stellar.dto.request.UserUpdateRequest;
import com.ecommerce.stellar.dto.request.UserPatchRequest;
import com.ecommerce.stellar.dto.response.UserResponse;
import com.ecommerce.stellar.entity.User;
import com.ecommerce.stellar.exception.ResourceNotFoundException;
import com.ecommerce.stellar.exception.UserAlreadyExistsException;
import com.ecommerce.stellar.repository.UserRepository;
import com.ecommerce.stellar.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserResponse create(UserCreateRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new UserAlreadyExistsException(request.email());
		}

		User user = new User();
		user.setFullName(request.fullName());
		user.setEmail(request.email());
		user.setPassword(passwordEncoder.encode(request.password()));
		return toResponse(userRepository.save(user));
	}

	@Transactional(readOnly = true)
	public List<UserResponse> findAll() {
		return userRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public UserResponse findById(Long userId) {
		return toResponse(getUser(userId));
	}

	public UserResponse update(Long userId, UserUpdateRequest request) {
		User user = getUser(userId);
		if (userRepository.existsByEmailAndUserIdNot(request.email(), userId)) {
			throw new UserAlreadyExistsException(request.email());
		}

		user.setFullName(request.fullName());
		user.setEmail(request.email());
		if (request.password() != null && !request.password().isBlank()) {
			user.setPassword(passwordEncoder.encode(request.password()));
		}
		return toResponse(userRepository.save(user));
	}

	@Override
	public UserResponse patch(Long userId, UserPatchRequest request) {
		User user = getUser(userId);
		if (request.email() != null && userRepository.existsByEmailAndUserIdNot(request.email(), userId)) {
			throw new UserAlreadyExistsException(request.email());
		}

		if (request.fullName() != null) {
			user.setFullName(request.fullName());
		}
		if (request.email() != null) {
			user.setEmail(request.email());
		}
		if (request.password() != null) {
			user.setPassword(passwordEncoder.encode(request.password()));
		}
		return toResponse(userRepository.save(user));
	}

	public void delete(Long userId) {
		userRepository.delete(getUser(userId));
	}

	private User getUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", userId));
	}

	private UserResponse toResponse(User user) {
		return new UserResponse(user.getUserId(), user.getFullName(), user.getEmail());
	}
}
