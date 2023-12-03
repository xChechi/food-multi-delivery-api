package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.ReviewConverter;
import io.chechi.delivery.dto.review.ReviewRequest;
import io.chechi.delivery.dto.review.ReviewResponse;
import io.chechi.delivery.entity.Review;
import io.chechi.delivery.exception.ReviewNotExistException;
import io.chechi.delivery.repository.ReviewRepository;
import io.chechi.delivery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;

    @Override
    public List<ReviewResponse> findAll() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream()
                .map(reviewConverter::toResponse)
                .toList();
    }

    @Override
    public ReviewResponse findById(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotExistException("Review not found"));
        return reviewConverter.toResponse(review);
    }

    @Override
    public ReviewResponse addReview(Integer restaurantId, ReviewRequest request) {
        Review review = reviewConverter.addReview(restaurantId, request);
        Review savedReview = reviewRepository.save(review);

        return reviewConverter.toResponse(savedReview);
    }

    @Override
    public ReviewResponse updateReview(Integer id, ReviewRequest request) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotExistException("Review not found"));

        review.setStars(request.getStars());
        review.setComment(request.getComment());
        Review savedReview = reviewRepository.save(review);

        return reviewConverter.toResponse(savedReview);
    }

    @Override
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewResponse> findByRestaurant(Integer restaurantId) {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream()
                .filter(f -> {
                    Integer id = f.getRestaurant().getId();
                    return id != null && id.equals(restaurantId);
                })
                .map(reviewConverter::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> findRecentlyReviewsByRestaurant(Integer restaurantId) {
        List<Review> reviewList = reviewRepository.findRecentlyReviewsByRestaurant(restaurantId);

        return reviewList.stream()
                .map(reviewConverter::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> findByStarForRestaurant(Integer restaurantId, Integer stars) {
        List<Review> reviewList = reviewRepository.findByRestaurantIdAndStars(restaurantId, stars);

        return reviewList.stream()
                .map(reviewConverter::toResponse)
                .toList();
    }
}
