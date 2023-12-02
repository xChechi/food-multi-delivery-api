package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.RestaurantConverter;
import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.exception.RestaurantNotExistException;
import io.chechi.delivery.repository.RestaurantRepository;
import io.chechi.delivery.service.RestaurantService;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantConverter restaurantConverter;
    private final MultipartFileToByteArrayConverter imageConverter;

    @Override
    public List<RestaurantDetailedResponse> findAll() {

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream()
                .map(restaurantConverter::toResponse)
                .toList();
    }

    @Override
    public RestaurantDetailedResponse findById(Integer id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));
        return restaurantConverter.toResponse(restaurant);
    }

    @Override
    public RestaurantDetailedResponse addRestaurant(RestaurantRequest request) {
        try {
            byte[] imageData = imageConverter.convert(request.getFile());
            //request.setFile(file); // Set the file in the request if needed in conversion

            Restaurant restaurant = restaurantConverter.addRestaurant(request, imageData);
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);
            return restaurantConverter.toResponse(savedRestaurant);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public void deleteById(Integer id) {

        restaurantRepository.deleteById(id);
    }
}
