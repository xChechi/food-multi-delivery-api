package io.chechi.delivery.controller;

import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDetailedResponse>> findAll () {
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailedResponse> findById (@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(restaurantService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDetailedResponse> addRestaurant (@ModelAttribute @RequestBody @Valid RestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.addRestaurant(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Integer id) {
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{restaurantId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantDetailedResponse> updateRestaurant (@PathVariable Integer restaurantId, @ModelAttribute @RequestBody @Valid RestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.updateRestaurant(restaurantId, request));
    }
}
