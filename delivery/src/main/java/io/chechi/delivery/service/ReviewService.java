package io.chechi.delivery.service;

import io.chechi.delivery.dto.review.ReviewRequest;
import io.chechi.delivery.dto.review.ReviewResponse;

import java.util.List;

public interface ReviewService {

    List<ReviewResponse> findAll ();

    ReviewResponse findById (Integer id);

    ReviewResponse addReview (Integer id, ReviewRequest request);

    ReviewResponse updateReview (Integer id, ReviewRequest request);

    void deleteReview (Integer id);

    // add search by restaurants
    List<ReviewResponse> findByRestaurant (Integer restaurantId);

    // add search recently posted
    List<ReviewResponse> findRecentlyReviewsByRestaurant (Integer restaurantId);

    // add search by stars for selected restaurant
    List<ReviewResponse> findByStarForRestaurant (Integer restaurantId, Integer stars);
}
