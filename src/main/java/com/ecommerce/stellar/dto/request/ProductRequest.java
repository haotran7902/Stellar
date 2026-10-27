package com.ecommerce.stellar.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(
		@NotBlank @Size(max = 150) String productName,
		String description,
		@Size(max = 2048) String image,
		@NotBlank @Size(max = 100) String sku,
		@NotNull @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 10, fraction = 2) BigDecimal price,
		@NotNull @Positive Long categoryId) {
}
