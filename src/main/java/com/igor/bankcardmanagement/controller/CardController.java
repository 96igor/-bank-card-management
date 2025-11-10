package com.igor.bankcardmanagement.controller;

import com.igor.bankcardmanagement.dto.CardDto;
import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.mapper.CardMapper;
import com.igor.bankcardmanagement.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    // 🔹 Ручной конструктор
    public CardController(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @PostMapping
    public CardDto create(@RequestBody Card card) {
        return cardMapper.toDto(cardService.create(card));
    }

    @GetMapping("/{id}")
    public CardDto get(@PathVariable Long id) {
        return cardMapper.toDto(cardService.getById(id));
    }

    @GetMapping
    public List<Card> getAll() {
        return cardService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<Card> getByUserId(@PathVariable Long userId) {
        return cardService.getByUserId(userId);
    }

    @PutMapping("/{id}/balance")
    public CardDto updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        return cardMapper.toDto(cardService.updateBalance(id, balance));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }
}

