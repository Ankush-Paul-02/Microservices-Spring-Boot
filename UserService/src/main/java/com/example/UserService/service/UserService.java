package com.example.UserService.service;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDto userDto);

    List<User> getAllUsers();

    User getUserById(Long id);
}
