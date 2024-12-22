package com.example.UserService.client;

import com.example.UserService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rating-service", url = "http://localhost:8093")
public interface RatingClient {

    @GetMapping("/api/rating/all/user/{id}")
    List<Rating> getRatingsByUserId(@PathVariable Long id);
}
