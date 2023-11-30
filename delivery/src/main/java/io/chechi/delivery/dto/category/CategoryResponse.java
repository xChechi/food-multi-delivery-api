package io.chechi.delivery.dto.category;

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

}
