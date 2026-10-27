package com.ecommerce.stellar.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CategoryPatchRequest(
		@Pattern(regexp = ".*\\S.*", message = "must not be blank") @Size(max = 100) String categoryName,
		String description) {
}
