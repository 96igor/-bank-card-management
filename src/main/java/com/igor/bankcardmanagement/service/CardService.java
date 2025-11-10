package com.igor.bankcardmanagement.service;

import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card getById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public List<Card> getByUserId(Long userId) {
        return cardRepository.findByUserId(userId);
    }

    public Card updateBalance(Long cardId, BigDecimal newBalance) {
        return cardRepository.findById(cardId).map(card -> {
            card.setBalance(newBalance);
            return cardRepository.save(card);
        }).orElse(null);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
