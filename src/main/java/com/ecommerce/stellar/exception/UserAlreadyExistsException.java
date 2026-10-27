package com.ecommerce.stellar.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {

	public UserAlreadyExistsException(String email) {
		super(HttpStatus.CONFLICT, "USER_ALREADY_EXISTS", "A user with this email already exists: " + email);
	}
}
