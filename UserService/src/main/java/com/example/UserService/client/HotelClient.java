package com.example.UserService.client;

import com.example.UserService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelClient {

    @GetMapping("/api/hotel/{id}")
    Hotel getHotelById(@PathVariable Long id);
}
