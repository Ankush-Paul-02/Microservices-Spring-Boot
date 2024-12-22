package com.example.HotelService.controller;

import com.example.HotelService.dto.HotelDto;
import com.example.HotelService.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.addHotel(hotelDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}
