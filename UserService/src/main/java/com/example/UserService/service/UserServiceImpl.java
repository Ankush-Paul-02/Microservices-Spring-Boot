package com.example.UserService.service;

import com.example.UserService.client.HotelClient;
import com.example.UserService.client.RatingClient;
import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.Hotel;
import com.example.UserService.entity.Rating;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private HotelClient hotelClient;

    @Override
    public User saveUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User is already present");
        }
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .skill(userDto.getSkill())
                .rating(new ArrayList<>())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User is not present");
        }

        List<Rating> ratings = ratingClient.getRatingsByUserId(id);
        ratings.forEach(rating -> {
            Hotel hotel = hotelClient.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
        });
        optionalUser.get().setRating(ratings);

        return optionalUser.get();
    }
}
