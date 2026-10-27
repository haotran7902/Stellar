package com.ecommerce.stellar.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
		Long productId,
		String productName,
		String description,
		String image,
		String sku,
		BigDecimal price,
		Long categoryId,
		String categoryName) {
}
