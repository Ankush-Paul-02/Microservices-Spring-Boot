package com.example.UserService.entity;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String name;
    private String location;
    private String about;
}
