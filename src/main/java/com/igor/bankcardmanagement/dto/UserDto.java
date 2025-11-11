package com.igor.bankcardmanagement.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Role cannot be blank")
    private String role;
}

