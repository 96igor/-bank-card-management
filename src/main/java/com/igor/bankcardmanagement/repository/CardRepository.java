package com.igor.bankcardmanagement.repository;

import com.igor.bankcardmanagement.entity.Card;
import com.igor.bankcardmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUser(User user);
}
