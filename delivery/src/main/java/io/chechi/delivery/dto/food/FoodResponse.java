package io.chechi.delivery.dto.food;

import io.chechi.delivery.dto.category.CategoryResponse;
import io.chechi.delivery.dto.category.CategoryShortResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodResponse {

    private Integer id;

    private String name;

    private String description;

    private String fullImageUrl;

    private Double price;

    private CategoryShortResponse category;

    private String restaurantName;
}
