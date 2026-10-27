package com.ecommerce.stellar.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductPatchRequest(
		@Pattern(regexp = ".*\\S.*", message = "must not be blank") @Size(max = 150) String productName,
		String description,
		@Size(max = 2048) String image,
		@Pattern(regexp = ".*\\S.*", message = "must not be blank") @Size(max = 100) String sku,
		@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 10, fraction = 2) BigDecimal price,
		@Positive Long categoryId) {
}
