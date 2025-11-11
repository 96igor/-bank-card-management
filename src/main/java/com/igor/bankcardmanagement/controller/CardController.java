package com.igor.bankcardmanagement.controller;

import com.igor.bankcardmanagement.dto.CardDto;
import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.mapper.CardMapper;
import com.igor.bankcardmanagement.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    public CardController(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @PostMapping
    public ResponseEntity<CardDto> create(@RequestBody Card card) {
        CardDto dto = cardMapper.toDto(cardService.create(card));
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> get(@PathVariable Long id) {
        Card card = cardService.getById(id);
        if (card == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cardMapper.toDto(card));
    }

    @GetMapping
    public List<CardDto> getAll() {
        return cardService.getAll().stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<CardDto> getByUserId(@PathVariable Long userId) {
        return cardService.getByUserId(userId).stream()
                .map(cardMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<CardDto> updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        Card updated = cardService.updateBalance(id, balance);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cardMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


