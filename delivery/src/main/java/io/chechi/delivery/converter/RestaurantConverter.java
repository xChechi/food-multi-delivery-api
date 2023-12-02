package io.chechi.delivery.converter;

import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.dto.restaurant.RestaurantShortResponse;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
@AllArgsConstructor
public class RestaurantConverter {

    private final MultipartFileToByteArrayConverter imageConverter;

    public Restaurant addRestaurant (RestaurantRequest request) {
        try {
            MultipartFile image = request.getFile();
            byte[] imageData = imageConverter.convert(image);

            return Restaurant.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .address(request.getAddress())
                    .phoneNumber(request.getPhoneNumber())
                    .email(request.getEmail())
                    .imageData(imageData)
                    .deliveryCost(request.getDeliveryCost())
                    .deliveryTime(request.getDeliveryTime())
                    //review stats
                    .minimumOrder(request.getMinimumOrder())
                    .build();
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    public RestaurantDetailedResponse toResponse (Restaurant restaurant) {

        byte[] imageData = restaurant.getImageData();
        String base64ImageData = Base64.getEncoder().encodeToString(imageData);

        return RestaurantDetailedResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .email(restaurant.getEmail())
                .imageData(base64ImageData)
                .deliveryCost(restaurant.getDeliveryCost())
                .deliveryTime(restaurant.getDeliveryTime())
                //review
                .minimumOrder(restaurant.getMinimumOrder())
                .build();
    }

    public RestaurantShortResponse toShortResponse (Restaurant restaurant) {

        return RestaurantShortResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .build();
    }
}
