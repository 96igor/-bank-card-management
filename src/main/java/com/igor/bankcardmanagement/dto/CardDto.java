package com.igor.bankcardmanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {
    private Long id;
    private String maskedNumber;
    private String owner;
    private String expirationDate;
    private String status;
    private BigDecimal balance;
    private Long userId;
}
