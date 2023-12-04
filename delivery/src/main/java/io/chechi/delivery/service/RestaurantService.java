package io.chechi.delivery.service;

import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDetailedResponse> findAll ();

    RestaurantDetailedResponse findById (Integer id);

    RestaurantDetailedResponse addRestaurant (RestaurantRequest request);

    void deleteById (Integer id);

    RestaurantDetailedResponse updateRestaurant (Integer restaurantId, RestaurantRequest request);
}
