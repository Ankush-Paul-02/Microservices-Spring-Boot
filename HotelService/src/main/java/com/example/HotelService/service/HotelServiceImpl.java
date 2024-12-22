package com.example.HotelService.service;

import com.example.HotelService.dto.HotelDto;
import com.example.HotelService.entity.Hotel;
import com.example.HotelService.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDto addHotel(HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findHotelByName(hotelDto.getName());
        if (optionalHotel.isPresent()) {
            throw new RuntimeException("Hotel is already present");
        }
        Hotel hotel = Hotel.builder()
                .name(hotelDto.getName())
                .about(hotelDto.getAbout())
                .location(hotelDto.getLocation())
                .build();
        hotelRepository.save(hotel);
        return hotelDto;
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> all = hotelRepository.findAll();
        return all.stream().map(hotel -> HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .about(hotel.getAbout())
                .location(hotel.getLocation())
                .build()).toList();
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            throw new RuntimeException("Hotel is not present");
        }
        Hotel hotel = optionalHotel.get();
        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .about(hotel.getAbout())
                .location(hotel.getLocation())
                .build();
    }
}
