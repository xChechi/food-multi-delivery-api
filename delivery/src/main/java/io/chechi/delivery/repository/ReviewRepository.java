package io.chechi.delivery.repository;

import io.chechi.delivery.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId ORDER BY r.createdAt DESC")
    List<Review> findRecentlyReviewsByRestaurant(Integer restaurantId);

    List<Review> findByRestaurantIdAndStars(Integer restaurantId, Integer stars);
}
