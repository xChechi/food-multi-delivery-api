package io.chechi.delivery.service;

import io.chechi.delivery.dto.category.CategoryRequest;
import io.chechi.delivery.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAllByRestaurant (Integer restaurantId);

    CategoryResponse addCategory (Integer restaurantId, CategoryRequest request);

    CategoryResponse updateCategory (Integer categoryId, CategoryRequest request);

    void deleteCategory (Integer categoryId);
}
