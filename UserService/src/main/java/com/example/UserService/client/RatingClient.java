package com.example.UserService.client;

import com.example.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingClient {

    @GetMapping("/api/rating/all/user/{id}")
    List<Rating> getRatingsByUserId(@PathVariable Long id);
}
