package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.TestConverter;
import io.chechi.delivery.dto.TestRequest;
import io.chechi.delivery.dto.TestResponse;
import io.chechi.delivery.entity.Restaurant;
import io.chechi.delivery.entity.Test;
import io.chechi.delivery.exception.ImageConversionException;
import io.chechi.delivery.repository.TestRepository;
import io.chechi.delivery.service.TestService;
import io.chechi.delivery.util.MultipartFileToByteArrayConverter;
import io.chechi.delivery.util.SaveImageToFile;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestConverter testConverter;
    private final MultipartFileToByteArrayConverter imageConverter;
    private final SaveImageToFile saveImageToFile;

    @Override
    public TestResponse addTest(TestRequest testRequest) {
        try {
            byte[] imageData = imageConverter.convert(testRequest.getFile());
            Test test = testConverter.addTest(testRequest, imageData);
            Test savedTest = testRepository.save(test);

            String imageUrl = saveImageToFile.saveToFile(test.getId(), testRequest.getFile());

            System.out.println("----------------------------->" + test.getId());

            TestResponse testResponse = testConverter.toResponse(savedTest, imageUrl);
            testResponse.setFullImageUrl(imageUrl);

            return testConverter.toResponse(savedTest, imageUrl);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

    /*
    @Override
    public TestResponse addTest(String name, MultipartFile file) {
        try {
            byte[] imageData = imageConverter.convert(file);
            Test test = Test.builder()
                    .name(name)
                    .imageData(imageData)
                    .build();
            Test savedTest = testRepository.save(test);
            return testConverter.toResponse(savedTest);
        } catch (IOException e) {
            throw new ImageConversionException("Error occurred during image conversion");
        }
    }

     */
}
