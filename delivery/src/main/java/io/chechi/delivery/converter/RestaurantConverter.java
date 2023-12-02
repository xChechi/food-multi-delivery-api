package io.chechi.delivery.converter;

import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.dto.restaurant.RestaurantShortResponse;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.util.CompressBase64String;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

@Component
@AllArgsConstructor
public class RestaurantConverter {

    private final CompressBase64String compressBase64String;

    public Restaurant addRestaurant (RestaurantRequest request, byte[] imageData) {

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
        }


    public RestaurantDetailedResponse toResponse (Restaurant restaurant) {

        byte[] imageData = restaurant.getImageData();
        String base64ImageData = Base64.getEncoder().encodeToString(imageData);

        String compressedBase64ImageData;
        try {
            compressedBase64ImageData = compressBase64String.convert(base64ImageData);
        } catch (IOException e) {
            // Handle compression error appropriately
            //throw new ImageCompressionException("Error compressing image data");
            throw new RuntimeException();
        }

        return RestaurantDetailedResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .email(restaurant.getEmail())
                .imageData(compressedBase64ImageData)
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
