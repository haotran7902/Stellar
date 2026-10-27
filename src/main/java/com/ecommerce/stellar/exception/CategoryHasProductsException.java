package com.ecommerce.stellar.exception;

import org.springframework.http.HttpStatus;

public class CategoryHasProductsException extends ApiException {

	public CategoryHasProductsException(Long categoryId) {
		super(HttpStatus.CONFLICT, "CATEGORY_HAS_PRODUCTS",
				"Cannot delete category " + categoryId + " because it still has products");
	}
}
