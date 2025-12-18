package com.coder2client.controllers;

import com.coder2client.dtos.RestaurantCreateUpdateRequest;
import com.coder2client.dtos.RestaurantCreateUpdateRequestDto;
import com.coder2client.dtos.RestaurantDto;
import com.coder2client.entities.Restaurant;
import com.coder2client.mappers.RestaurantMapper;
import com.coder2client.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@Valid @RequestBody RestaurantCreateUpdateRequestDto request){

        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper.toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);

        RestaurantDto restaurantDto = restaurantMapper.toRestaurantDto(restaurant);

        return ResponseEntity.ok(restaurantDto);

    }

}
