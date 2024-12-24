package com.example.UserService.controller;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelCircuitBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    public ResponseEntity<User> ratingHotelFallback(Long id, Exception e) {
        log.info("Fallback executed for User ID: {}. Reason: {}", id, e.getMessage());

        // Create a dummy user as a fallback response
        User fallbackUser = User.builder()
                .id(null)
                .name("Dummy User")
                .email("dummy@gmail.com")
                .skill("Fallback Skill")
                .build();

        // Return a fallback response with a 503 Service Unavailable status
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(fallbackUser);
    }

}
