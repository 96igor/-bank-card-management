package com.igor.bankcardmanagement.controller;

import com.igor.bankcardmanagement.dto.CardDto;
import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.mapper.CardMapper;
import com.igor.bankcardmanagement.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    @PostMapping
    public CardDto create(@RequestBody CardDto cardDto) {
        Card card = cardMapper.toEntity(cardDto);
        Card created = cardService.create(card);
        return cardMapper.toDto(created);
    }

    @GetMapping("/{id}")
    public CardDto getById(@PathVariable Long id) {
        return cardMapper.toDto(cardService.getById(id));
    }

    @GetMapping
    public List<CardDto> getAll() {
        return cardService.getAll()
                .stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<CardDto> getByUserId(@PathVariable Long userId) {
        return cardService.getByUserId(userId)
                .stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{cardId}/balance")
    public CardDto updateBalance(@PathVariable Long cardId, @RequestParam BigDecimal newBalance) {
        Card updated = cardService.updateBalance(cardId, newBalance);
        return cardMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cardService.delete(id);
    }
}

