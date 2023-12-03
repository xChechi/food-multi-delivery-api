package io.chechi.delivery.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer stars;

    @NotBlank
    @Length(max = 255)
    private String comment;
}
