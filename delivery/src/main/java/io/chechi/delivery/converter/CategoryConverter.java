package io.chechi.delivery.converter;

import io.chechi.delivery.dto.category.CategoryRequest;
import io.chechi.delivery.dto.category.CategoryResponse;
import io.chechi.delivery.entity.Category;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.exception.RestaurantNotExistException;
import io.chechi.delivery.repository.RestaurantRepository;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CategoryConverter {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantConverter restaurantConverter;

    public Category createCategory (Integer restaurantId, CategoryRequest request, byte[] imageData) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));

        return Category.builder()
                .restaurant(restaurant)
                .name(request.getName())
                .description(request.getDescription())
                .imageData(imageData)
                .build();
    }

    public CategoryResponse toResponse (Category category, String imageUrl) {

        return CategoryResponse.builder()
                .id(category.getId())
                .restaurant(restaurantConverter.toShortResponse(category.getRestaurant()))
                .name(category.getName())
                .description(category.getDescription())
                .fullImageUrl(imageUrl)
                .build();
    }
}
