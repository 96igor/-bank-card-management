package com.igor.bankcardmanagement.mapper;

import com.igor.bankcardmanagement.dto.CardDto;
import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDto toDto(Card card) {
        if (card == null) return null;
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setMaskedNumber(card.getMaskedNumber());
        dto.setOwner(card.getOwner());
        dto.setExpirationDate(card.getExpirationDate());
        dto.setStatus(card.getStatus());
        dto.setBalance(card.getBalance());
        dto.setUserId(card.getUser() != null ? card.getUser().getId() : null);
        return dto;
    }

    public Card toEntity(CardDto dto, User user) {
        if (dto == null) return null;
        Card card = new Card();
        card.setId(dto.getId());
        card.setMaskedNumber(dto.getMaskedNumber());
        card.setOwner(dto.getOwner());
        card.setExpirationDate(dto.getExpirationDate());
        card.setStatus(dto.getStatus());
        card.setBalance(dto.getBalance());
        card.setUser(user);
        return card;
    }
}
