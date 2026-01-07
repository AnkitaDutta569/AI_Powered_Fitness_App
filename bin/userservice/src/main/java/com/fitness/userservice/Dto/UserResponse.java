package com.fitness.userservice.Dto;

import com.fitness.userservice.Model.UserRole;

import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role = UserRole.USER;
    private LocalDateTime creationTime;
    private LocalDateTime updationTime;
}
