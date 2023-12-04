package io.chechi.delivery.dto.food;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
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
public class FoodRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Length(max = 255)
    private String description;

    private MultipartFile file;

    @NotNull
    private Double price;

    private boolean addExtras;
}
