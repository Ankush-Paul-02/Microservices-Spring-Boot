package com.example.RatingService.controller;

import com.example.RatingService.entity.Rating;
import com.example.RatingService.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/save")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.createRating(rating));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/all/user/{id}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getAllByUserId(id));
    }

    @GetMapping("/all/hotel/{id}")
    public ResponseEntity<List<Rating>> getAllRatings(@PathVariable Long id) {
        return ResponseEntity.ok(ratingService.getAllByHotelId(id));
    }
}


