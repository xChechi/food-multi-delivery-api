package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.CategoryConverter;
import io.chechi.delivery.dto.category.CategoryRequest;
import io.chechi.delivery.dto.category.CategoryResponse;
import io.chechi.delivery.entity.Category;
import io.chechi.delivery.exception.CategoryNotFoundException;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.repository.CategoryRepository;
import io.chechi.delivery.service.CategoryService;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import io.chechi.delivery.util.SaveImageToFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;
    private final MultipartFileToByteArrayConverter imageConverter;
    private final SaveImageToFile saveImageToFile;

    @Override
    public List<CategoryResponse> findAllByRestaurant(Integer restaurantId) {

        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
                .filter(f -> {
                    Integer id = f.getRestaurant().getId();
                    return id != null && id.equals(restaurantId);
                })
                .map(category -> categoryConverter.toResponse(category, saveImageToFile.imageUrlFor(category.getId(), category.getName())))
                .toList();
    }

    @Override
    public CategoryResponse addCategory(Integer restaurantId, CategoryRequest request) {
        try {
            byte[] imageData = imageConverter.convert(request.getFile());

            Category category = categoryConverter.createCategory(restaurantId, request, imageData);
            Category savedCategory = categoryRepository.save(category);

            String imageUrl = saveImageToFile.saveToFile(category.getId(), category.getName(), request.getFile());

            CategoryResponse response = categoryConverter.toResponse(savedCategory, imageUrl);
            response.setFullImageUrl(imageUrl);

            return categoryConverter.toResponse(savedCategory, imageUrl);

        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public CategoryResponse updateCategory(Integer categoryId, CategoryRequest request) {
        try {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not exist"));

            category.setName(request.getName());
            category.setDescription(request.getDescription());

            String imageUrl;
            if (request.getFile() != null) {

                imageUrl = saveImageToFile.saveToFile(category.getId(), category.getName(), request.getFile());

            } else {
                imageUrl = saveImageToFile.imageUrlFor(category.getId(), category.getName());
            }

            Category savedCategory = categoryRepository.save(category);

            return categoryConverter.toResponse(savedCategory, imageUrl);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
