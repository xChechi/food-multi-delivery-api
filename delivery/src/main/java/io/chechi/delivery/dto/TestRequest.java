package io.chechi.delivery.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestRequest {

    private String name;

    private MultipartFile file;
}
