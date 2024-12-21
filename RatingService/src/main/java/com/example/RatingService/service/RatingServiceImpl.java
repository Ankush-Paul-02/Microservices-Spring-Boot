package com.example.RatingService.service;

import com.example.RatingService.entity.Rating;
import com.example.RatingService.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotelId(Long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
