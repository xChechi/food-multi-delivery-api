package io.chechi.delivery.converter;

import io.chechi.delivery.dto.restaurant.RestaurantDetailedResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.dto.restaurant.RestaurantShortResponse;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.entity.Review;
import io.chechi.delivery.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class RestaurantConverter {

    private final ReviewRepository reviewRepository;

    public Restaurant addRestaurant (RestaurantRequest request, byte[] imageData) {

            return Restaurant.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .address(request.getAddress())
                    .phoneNumber(request.getPhoneNumber())
                    .email(request.getEmail())
                    .imageData(imageData)
                    .deliveryCost(request.getDeliveryCost())
                    .deliveryTime(request.getDeliveryTime())
                    .minimumOrder(request.getMinimumOrder())
                    .build();
        }


    public RestaurantDetailedResponse toResponse (Restaurant restaurant, String imageUrl) {

        List<Review> reviewList = reviewRepository.findRecentlyReviewsByRestaurant(restaurant.getId());

        double averageStars = reviewList.stream().mapToInt(Review::getStars).average().orElse(0.0);

        return RestaurantDetailedResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .email(restaurant.getEmail())
                .imageUrl(imageUrl)
                .deliveryCost(restaurant.getDeliveryCost())
                .deliveryTime(restaurant.getDeliveryTime())
                .reviewStat(Math.round(averageStars * 100.0) / 100.0)
                .minimumOrder(restaurant.getMinimumOrder())
                .build();
    }

    public RestaurantShortResponse toShortResponse (Restaurant restaurant) {

        List<Review> reviewList = reviewRepository.findRecentlyReviewsByRestaurant(restaurant.getId());

        double averageStars = reviewList.stream().mapToInt(Review::getStars).average().orElse(0.0);

        return RestaurantShortResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .reviewStat(Math.round(averageStars * 100.0) / 100.0)
                .build();
    }


}
