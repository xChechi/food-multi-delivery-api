package io.chechi.delivery.dto.restaurant;

import io.chechi.delivery.util.DurationConverter;
import io.chechi.delivery.annotation.PhoneNumberValidation;
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
public class RestaurantRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String address;

    @PhoneNumberValidation
    private String phoneNumber;

    @Email
    private String email;

    private MultipartFile file;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @NotNull
    private Double deliveryCost;

    @NotNull
    private Double reviewStat;

    @NotNull
    private Double minimumOrder;

    @Convert(converter = DurationConverter.class)
    private Duration deliveryTime;
}
