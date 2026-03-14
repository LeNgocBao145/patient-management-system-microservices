package com.example.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Min(value = 8, message = "Password must be at least 8 characters long")
    private String password;

    public @NotBlank @Email(message = "Email is required") String getEmail() {
        return email;
    }

    public @NotBlank @Min(value = 8, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Min(value = 8, message = "Password must be at least 8 characters long") String password) {
        this.password = password;
    }

    public void setEmail(@NotBlank @Email(message = "Email is required") String email) {
        this.email = email;
    }
}
