package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.FoodConverter;
import io.chechi.delivery.dto.food.FoodRequest;
import io.chechi.delivery.dto.food.FoodResponse;
import io.chechi.delivery.entity.Food;
import io.chechi.delivery.exception.FoodNotFoundException;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.repository.FoodRepository;
import io.chechi.delivery.service.FoodService;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import io.chechi.delivery.util.SaveImageToFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodConverter foodConverter;
    private final MultipartFileToByteArrayConverter imageConverter;
    private final SaveImageToFile saveImageToFile;

    @Override
    public List<FoodResponse> findAll() {
        List<Food> foodList = foodRepository.findAll();

        return foodList.stream()
                .map(food -> foodConverter.toResponse(food, saveImageToFile.imageUrlFor(food.getId(), food.getName())))
                .toList();
    }

    @Override
    public FoodResponse findById(Integer foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new FoodNotFoundException("Food not found"));
        return foodConverter.toResponse(food, saveImageToFile.imageUrlFor(food.getId(), food.getName()));
    }

    @Override
    public FoodResponse addFood(Integer categoryId, FoodRequest request) {
        try {
            byte[] imageData = imageConverter.convert(request.getFile());

            Food food = foodConverter.addFood(categoryId, request, imageData);
            Food savedFood = foodRepository.save(food);

            String imageUrl = saveImageToFile.saveToFile(food.getId(), food.getName(), request.getFile());

            FoodResponse response = foodConverter.toResponse(savedFood, imageUrl);
            response.setFullImageUrl(imageUrl);

            return response;
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public FoodResponse updateFood(Integer foodId, FoodRequest request) {
        try {
            Food food = foodRepository.findById(foodId).orElseThrow(() -> new FoodNotFoundException("Food not found"));

            food.setName(request.getName());
            food.setDescription(request.getDescription());
            food.setPrice(request.getPrice());
            food.setAddExtras(request.isAddExtras());

            String imageUrl;
            if (request.getFile() != null) {

                imageUrl = saveImageToFile.saveToFile(food.getId(), food.getName(), request.getFile());

            } else {
                imageUrl = saveImageToFile.imageUrlFor(food.getId(), food.getName());
            }

            Food savedFood = foodRepository.save(food);

            return foodConverter.toResponse(savedFood, imageUrl);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    @Override
    public void deleteFood(Integer foodId) {
        foodRepository.deleteById(foodId);
    }

    @Override
    public List<FoodResponse> searchByRestaurant(Integer restaurantId) {
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream()
                .filter(food -> {
                    Integer resId = food.getCategory().getRestaurant().getId();
                    return resId != null && resId.equals(restaurantId);
                })
                .map(food -> foodConverter.toResponse(food, saveImageToFile.imageUrlFor(food.getId(), food.getName())))
                .toList();
    }

    @Override
    public List<FoodResponse> searchByCategory(Integer categoryId) {
        List<Food> foodList = foodRepository.findAll();
        return foodList.stream()
                .filter(food -> {
                    Integer catId = food.getCategory().getId();
                    return  catId != null && catId.equals(categoryId);
                })
                .map(food -> foodConverter.toResponse(food, saveImageToFile.imageUrlFor(food.getId(), food.getName())))
                .toList();
    }

}
