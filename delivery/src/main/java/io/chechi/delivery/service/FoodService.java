package io.chechi.delivery.service;

import io.chechi.delivery.dto.food.FoodRequest;
import io.chechi.delivery.dto.food.FoodResponse;

import java.util.List;

public interface FoodService {

    List<FoodResponse> findAll ();

    FoodResponse findById (Integer foodId);

    FoodResponse addFood (Integer categoryId, FoodRequest request);

    FoodResponse updateFood (Integer foodId, FoodRequest request);

    void deleteFood (Integer foodId);

    List<FoodResponse> searchByRestaurant (Integer restaurantId);

    List<FoodResponse> searchByCategory (Integer categoryId);

}
