package com.igor.bankcardmanagement.controller;

import com.igor.bankcardmanagement.dto.CardDto;
import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.entity.User;
import com.igor.bankcardmanagement.mapper.CardMapper;
import com.igor.bankcardmanagement.service.CardService;
import com.igor.bankcardmanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CardDto> create(@Valid @RequestBody CardDto cardDto) {
        User user = userService.getById(cardDto.getUserId());
        if (user == null) return ResponseEntity.badRequest().build();
        Card card = cardMapper.toEntity(cardDto, user);
        Card saved = cardService.create(card);
        return ResponseEntity.ok(cardMapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> get(@PathVariable Long id) {
        Card card = cardService.getById(id);
        if (card == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cardMapper.toDto(card));
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAll() {
        List<CardDto> list = cardService.getAll().stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CardDto>> getByUserId(@PathVariable Long userId) {
        List<CardDto> list = cardService.getByUserId(userId).stream()
                .map(cardMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
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

