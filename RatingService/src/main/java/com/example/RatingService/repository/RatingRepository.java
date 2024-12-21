package com.example.RatingService.repository;

import com.example.RatingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByUserId(Long userId);

    List<Rating> findByHotelId(Long hotelId);
}
