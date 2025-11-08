package com.igor.bankcardmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String encryptedNumber;
    private String maskedNumber;

    private String owner;

    private String expirationDate;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
