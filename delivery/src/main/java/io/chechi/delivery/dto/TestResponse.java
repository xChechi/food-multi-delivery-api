package io.chechi.delivery.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestResponse {

    private Integer id;

    private String name;

    private String fullImageUrl;

}
