package io.chechi.delivery.converter;

import io.chechi.delivery.dto.review.ReviewRequest;
import io.chechi.delivery.dto.review.ReviewResponse;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.entity.Review;
import io.chechi.delivery.exception.RestaurantNotExistException;
import io.chechi.delivery.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewConverter {

    private final RestaurantRepository restaurantRepository;

    public Review addReview (ReviewRequest request, Integer restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotExistException("Restaurant not found"));

        return Review.builder()
                .restaurant(restaurant)
                .stars(request.getStars())
                .comment(request.getComment())
                .build();
    }

    public ReviewResponse toResponse (Review review) {

        return ReviewResponse.builder()
                .id(review.getId())
                .stars(review.getStars())
                .comment(review.getComment())
                .build();
    }
}
