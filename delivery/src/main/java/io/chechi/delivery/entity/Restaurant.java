package io.chechi.delivery.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.chechi.delivery.util.DurationConverter;
import io.chechi.delivery.annotation.PhoneNumberValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer id;

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

    @Transient
    private MultipartFile file;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @NotNull
    private Double deliveryCost;

    //@NotNull
    //private Double reviewStat;

    @NotNull
    private Double minimumOrder;

    @Convert(converter = DurationConverter.class)
    private Duration deliveryTime;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Category> categoryList;
}
