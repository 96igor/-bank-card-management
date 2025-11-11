package com.igor.bankcardmanagement.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class CardDto {
    private Long id;

    @NotBlank(message = "Masked number cannot be blank")
    private String maskedNumber;

    @NotBlank(message = "Owner cannot be blank")
    private String owner;

    @NotBlank(message = "Expiration date cannot be blank")
    private String expirationDate;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
