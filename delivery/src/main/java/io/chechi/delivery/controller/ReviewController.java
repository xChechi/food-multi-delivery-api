package io.chechi.delivery.controller;

import io.chechi.delivery.dto.review.ReviewRequest;
import io.chechi.delivery.dto.review.ReviewResponse;
import io.chechi.delivery.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> findAll () {
        return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findAll());
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> findById (@PathVariable Integer reviewId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findById(reviewId));
    }

    @PostMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ReviewResponse> addReview (@PathVariable Integer restaurantId, @RequestBody @Valid ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(restaurantId, request));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview (@PathVariable Integer reviewId, @RequestBody @Valid ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.updateReview(reviewId, request));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview (@PathVariable Integer reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<ReviewResponse>> findByRestaurant (@PathVariable Integer restaurantId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findByRestaurant(restaurantId));
    }

    @GetMapping("/restaurants/{restaurantId}/recent")
    public ResponseEntity<List<ReviewResponse>> findRecentReviewsByRestaurant (@PathVariable Integer restaurantId) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findRecentlyReviewsByRestaurant(restaurantId));
    }

    @GetMapping("/restaurants/{restaurantId}/stars/{stars}")
    public ResponseEntity<List<ReviewResponse>> findReviewsByStars (@PathVariable Integer restaurantId, @PathVariable Integer stars) {
        return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findByStarForRestaurant(restaurantId, stars));
    }
}
