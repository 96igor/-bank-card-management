package com.igor.bankcardmanagement.mapper;

import com.igor.bankcardmanagement.dto.UserDto;
import com.igor.bankcardmanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}
