package io.chechi.delivery.converter;

import io.chechi.delivery.dto.TestRequest;
import io.chechi.delivery.dto.TestResponse;
import io.chechi.delivery.entity.Test;
import io.chechi.delivery.util.SaveImageToFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@AllArgsConstructor
public class TestConverter {

    private final SaveImageToFile saveImageToFile;

    public Test addTest (TestRequest testRequest, byte[] imageData) {
        return Test.builder()
                .name(testRequest.getName())
                .imageData(imageData)
                .build();
    }

    public TestResponse toResponse(Test test, String imageUrl) {
        byte[] imageData = test.getImageData();
        String base64ImageData = Base64.getEncoder().encodeToString(imageData);


        return TestResponse.builder()
                .id(test.getId())
                .name(test.getName())
                .fullImageUrl(imageUrl)
                //.imageData(base64ImageData)
                .build();
    }
}
