package io.chechi.delivery.converter;

import io.chechi.delivery.dto.category.CategoryRequest;
import io.chechi.delivery.dto.category.CategoryResponse;
import io.chechi.delivery.entity.Category;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryConverter {

    private final RestaurantRepository restaurantRepository;

    public Category createCategory (Integer restaurantId, CategoryRequest request) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .imageData(request.getImageData())
                .restaurant(restaurant)
                .build();
    }

    public CategoryResponse toResponse (Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())

                .build();
    }
}
