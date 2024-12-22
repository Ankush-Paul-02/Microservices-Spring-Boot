package com.example.HotelService.service;

import com.example.HotelService.dto.HotelDto;
import com.example.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {

    HotelDto addHotel(HotelDto hotelDto);

    List<HotelDto> getAllHotels();

    HotelDto getHotelById(Long id);
}
