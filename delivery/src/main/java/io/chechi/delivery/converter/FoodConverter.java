package io.chechi.delivery.converter;

import io.chechi.delivery.dto.food.FoodRequest;
import io.chechi.delivery.dto.food.FoodResponse;
import io.chechi.delivery.entity.Category;
import io.chechi.delivery.entity.Food;
import io.chechi.delivery.exception.CategoryNotFoundException;
import io.chechi.delivery.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodConverter {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public Food addFood (Integer categoryId, FoodRequest request, byte[] imageData) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        return Food.builder()
                .category(category)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageData(imageData)
                .addExtras(request.isAddExtras())
                .build();
    }

    public FoodResponse toResponse (Food food, String imageUrl) {

        return FoodResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .price(food.getPrice())
                .fullImageUrl(imageUrl)
                .category(categoryConverter.toShortResponse(food.getCategory()))
                .restaurantName(food.getCategory().getRestaurant().getName())
                .build();
    }
}
