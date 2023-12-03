package io.chechi.delivery.dto.review;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewRequest {

    private Integer stars;

    private String comment;
}
