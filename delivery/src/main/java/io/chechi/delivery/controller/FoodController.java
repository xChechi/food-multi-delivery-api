package io.chechi.delivery.controller;

import io.chechi.delivery.dto.category.CategoryRequest;
import io.chechi.delivery.dto.food.FoodRequest;
import io.chechi.delivery.dto.food.FoodResponse;
import io.chechi.delivery.service.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}")
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/foods")
    public ResponseEntity<List<FoodResponse>> findAllByRestaurant (@PathVariable Integer restaurantId) {
        return ResponseEntity.status(HttpStatus.OK).body(foodService.searchByRestaurant(restaurantId));
    }

    @PostMapping(value = "/categories/{categoryId}/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodResponse> addFood (@PathVariable Integer categoryId, @ModelAttribute @RequestBody @Valid FoodRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.addFood(categoryId, request));
    }
}
