package com.halilkrkn.BooksyMate.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticateRequest {
    @Email(message = "Email is not formatted correctly")
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
