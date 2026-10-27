package com.ecommerce.stellar.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class GlobalExceptionHandlerTests {

	private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

	@Test
	void duplicateSkuReturnsConflictErrorResponse() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/products");

		ResponseEntity<ApiErrorResponse> response = exceptionHandler.handleApiException(
				new DuplicateSkuException("SKU-123"),
				request);

		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
		assertEquals("DUPLICATE_SKU", response.getBody().code());
		assertEquals("/api/products", response.getBody().path());
	}

	@Test
	void missingResourceReturnsNotFoundErrorResponse() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/users/99");

		ResponseEntity<ApiErrorResponse> response = exceptionHandler.handleApiException(
				new ResourceNotFoundException("User", 99),
				request);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("RESOURCE_NOT_FOUND", response.getBody().code());
		assertEquals("User not found with id: 99", response.getBody().message());
	}
}
