package com.coder2client.services;

import com.coder2client.dtos.RestaurantCreateUpdateRequest;
import com.coder2client.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantCreateUpdateRequest request);
}
