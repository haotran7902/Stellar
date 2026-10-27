package com.ecommerce.stellar.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.stellar.dto.request.UserCreateRequest;
import com.ecommerce.stellar.dto.request.UserUpdateRequest;
import com.ecommerce.stellar.dto.request.UserPatchRequest;
import com.ecommerce.stellar.dto.response.UserResponse;
import com.ecommerce.stellar.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@Valid @RequestBody UserCreateRequest request) {
		return userService.create(request);
	}

	@GetMapping
	public List<UserResponse> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{userId}")
	public UserResponse findById(@PathVariable Long userId) {
		return userService.findById(userId);
	}

	@PutMapping("/{userId}")
	public UserResponse update(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest request) {
		return userService.update(userId, request);
	}

	@PatchMapping("/{userId}")
	public UserResponse patch(@PathVariable Long userId, @Valid @RequestBody UserPatchRequest request) {
		return userService.patch(userId, request);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long userId) {
		userService.delete(userId);
	}
}
