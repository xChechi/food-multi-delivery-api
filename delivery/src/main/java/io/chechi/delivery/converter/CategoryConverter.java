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
    private final MultipartFileToByteArrayConverter imageConverter;

    public Category createCategory (Integer restaurantId, CategoryRequest request) {

        try {
            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));

            MultipartFile image = request.getFile();
            byte[] imageData = imageConverter.convert(image);

            return Category.builder()
                    .restaurant(restaurant)
                    .name(request.getName())
                    .description(request.getDescription())
                    .imageData(imageData)
                    .build();
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    public CategoryResponse toResponse (Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .restaurant(restaurantConverter.toShortResponse(category.getRestaurant()))
                .build();
    }
}
