package io.chechi.delivery.dto.extra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExtraResponse {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private Double price;
}
