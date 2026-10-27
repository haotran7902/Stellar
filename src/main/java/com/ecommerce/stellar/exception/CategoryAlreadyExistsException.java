package com.ecommerce.stellar.exception;

import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistsException extends ApiException {

	public CategoryAlreadyExistsException(String categoryName) {
		super(HttpStatus.CONFLICT, "CATEGORY_ALREADY_EXISTS",
				"A category with this name already exists: " + categoryName);
	}
}
