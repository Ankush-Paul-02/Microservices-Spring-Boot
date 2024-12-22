package com.example.UserService.entity;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String id;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
