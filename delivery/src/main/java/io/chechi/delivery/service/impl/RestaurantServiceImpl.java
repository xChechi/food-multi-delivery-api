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
import io.chechi.delivery.util.SaveImageToFile;
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
    private final SaveImageToFile saveImageToFile;

    @Override
    public List<RestaurantDetailedResponse> findAll() {

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream()
                .map(restaurant -> restaurantConverter.toResponse(restaurant, saveImageToFile.imageUrlFor(restaurant.getId(), restaurant.getName())))
                .toList();
    }

    @Override
    public RestaurantDetailedResponse findById(Integer id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));

        return restaurantConverter.toResponse(restaurant, saveImageToFile.imageUrlFor(restaurant.getId(), restaurant.getName()));
    }

    @Override
    public RestaurantDetailedResponse addRestaurant(RestaurantRequest request) {
        try {
            byte[] imageData = imageConverter.convert(request.getFile());

            Restaurant restaurant = restaurantConverter.addRestaurant(request, imageData);
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);

            String imageUrl = saveImageToFile.saveToFile(restaurant.getId(), restaurant.getName(), request.getFile());

            RestaurantDetailedResponse response = restaurantConverter.toResponse(savedRestaurant, imageUrl);
            response.setImageUrl(imageUrl);

            return restaurantConverter.toResponse(savedRestaurant, imageUrl);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public void deleteById(Integer id) {

        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantDetailedResponse updateRestaurant(Integer restaurantId, RestaurantRequest request) {
        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));

            restaurant.setName(request.getName());
            restaurant.setDescription(request.getDescription());
            restaurant.setAddress(request.getAddress());
            restaurant.setPhoneNumber(request.getPhoneNumber());
            restaurant.setEmail(request.getEmail());
            restaurant.setFile(request.getFile());
            restaurant.setDeliveryCost(request.getDeliveryCost());
            restaurant.setMinimumOrder(request.getMinimumOrder());
            restaurant.setDeliveryTime(request.getDeliveryTime());

            String imageUrl;
            if (request.getFile() != null) {

                imageUrl = saveImageToFile.saveToFile(restaurant.getId(), restaurant.getName(), request.getFile());

            } else {
                imageUrl = saveImageToFile.imageUrlFor(restaurant.getId(), restaurant.getName());
            }

            Restaurant savedRestaurant = restaurantRepository.save(restaurant);

            return restaurantConverter.toResponse(savedRestaurant, imageUrl);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }
}
