package com.igor.bankcardmanagement.controller;

import com.igor.bankcardmanagement.dto.UserDto;
import com.igor.bankcardmanagement.entity.User;
import com.igor.bankcardmanagement.mapper.UserMapper;
import com.igor.bankcardmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto create(@RequestBody User user) {
        return userMapper.toDto(userService.create(user));
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userMapper.toDto(userService.getById(id));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
