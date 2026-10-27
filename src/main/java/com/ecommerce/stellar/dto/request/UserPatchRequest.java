package com.ecommerce.stellar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserPatchRequest(
		@Pattern(regexp = ".*\\S.*", message = "must not be blank") @Size(max = 100) String fullName,
		@Email @Size(max = 254) String email,
		@Size(min = 8, max = 72) String password) {
}
