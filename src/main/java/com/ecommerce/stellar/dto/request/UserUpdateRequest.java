package com.ecommerce.stellar.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
		@NotBlank @Size(max = 100) String fullName,
		@NotBlank @Email @Size(max = 254) String email,
		@Pattern(regexp = "^$|.{8,72}", message = "must be empty or between 8 and 72 characters") String password) {
}
