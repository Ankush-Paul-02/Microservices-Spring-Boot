package com.example.UserService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private String name;
    private String email;
    private String skill;
}
