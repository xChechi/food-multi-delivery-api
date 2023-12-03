package io.chechi.delivery.dto.category;

import io.chechi.delivery.dto.restaurant.RestaurantShortResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {

    private Integer id;

    private String name;

    private String description;

    private String fullImageUrl;

    private RestaurantShortResponse restaurant;

}
