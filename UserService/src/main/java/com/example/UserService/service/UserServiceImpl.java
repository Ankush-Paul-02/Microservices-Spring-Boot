package com.example.UserService.service;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User is already present");
        }
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .skill(userDto.getSkill())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User is not present");
        }
        return optionalUser.get();
    }
}
