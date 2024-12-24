package com.example.UserService.controller;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.User;
import com.example.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "handleCircuitBreakerFallback")
    @Retry(name = "userServiceRetry", fallbackMethod = "handleRetryFallback")
    @RateLimiter(name = "userServiceRateLimiter", fallbackMethod = "handleRateLimiterFallback")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Fallback Methods
    public ResponseEntity<User> handleCircuitBreakerFallback(Long id, Exception e) {
        log.info("Circuit Breaker Fallback executed for User ID: {}. Reason: {}", id, e.getMessage());
        User fallbackUser = createFallbackUser("Circuit Breaker User", "circuitbreaker@gmail.com", "Default Skill - Circuit Breaker");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackUser);
    }

    public ResponseEntity<User> handleRetryFallback(Long id, Exception e) {
        log.info("Retry Fallback executed for User ID: {}. Reason: {}", id, e.getMessage());
        User fallbackUser = createFallbackUser("Retry User", "retry@gmail.com", "Default Skill - Retry");
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(fallbackUser);
    }

    public ResponseEntity<User> handleRateLimiterFallback(Long id, Exception e) {
        log.info("Rate Limiter Fallback executed for User ID: {}. Reason: {}", id, e.getMessage());
        User fallbackUser = createFallbackUser("Rate Limited User", "ratelimited@gmail.com", "Default Skill - Rate Limiter");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(fallbackUser);
    }

    private User createFallbackUser(String name, String email, String skill) {
        return User.builder()
                .id(null)
                .name(name)
                .email(email)
                .skill(skill)
                .build();
    }
}
