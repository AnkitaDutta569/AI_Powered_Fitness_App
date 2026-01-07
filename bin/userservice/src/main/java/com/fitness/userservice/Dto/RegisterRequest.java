package com.fitness.userservice.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 6,message = "Password should atleast contin 6 character")
    private String password;
    private String firstName;
    private String lastName;
}
