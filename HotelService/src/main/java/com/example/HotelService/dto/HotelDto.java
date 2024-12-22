package com.example.HotelService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private String about;
}
