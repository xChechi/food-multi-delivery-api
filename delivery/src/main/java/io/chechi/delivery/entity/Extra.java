package io.chechi.delivery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "extras")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_id")
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
