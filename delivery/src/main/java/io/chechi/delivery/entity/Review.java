package io.chechi.delivery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer stars;

    @NotBlank
    @Length(max = 255)
    private String comment;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
