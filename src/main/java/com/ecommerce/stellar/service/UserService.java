package com.ecommerce.stellar.service;

import java.util.List;

import com.ecommerce.stellar.dto.request.UserCreateRequest;
import com.ecommerce.stellar.dto.request.UserUpdateRequest;
import com.ecommerce.stellar.dto.request.UserPatchRequest;
import com.ecommerce.stellar.dto.response.UserResponse;

public interface UserService {
	UserResponse create(UserCreateRequest request);

	List<UserResponse> findAll();

	UserResponse findById(Long userId);

	UserResponse update(Long userId, UserUpdateRequest request);

	UserResponse patch(Long userId, UserPatchRequest request);

	void delete(Long userId);
}
