package io.chechi.delivery.dto.review;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewResponse {

    private Integer id;

    private Integer stars;

    private String comment;
}
