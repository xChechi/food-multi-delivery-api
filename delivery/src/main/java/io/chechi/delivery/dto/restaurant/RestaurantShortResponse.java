package io.chechi.delivery.dto.restaurant;

import io.chechi.delivery.annotation.PhoneNumberValidation;
import io.chechi.delivery.util.DurationConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RestaurantShortResponse {

    private Integer id;

    private String name;

    private String description;

    private Double reviewStat;

}
