package com.example.RatingService.service;

import com.example.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getAllByUserId(Long userId);

    List<Rating> getAllByHotelId(Long hotelId);
}
