package io.chechi.delivery.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 2)
    private String description;

    private MultipartFile file;


}
