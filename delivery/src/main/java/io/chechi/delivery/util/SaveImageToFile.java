package io.chechi.delivery.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class SaveImageToFile {

    @Value("${image.upload.directory}")
    private String imageDirectory;

    public String saveToFile (Integer testId, MultipartFile file) throws IOException {

        Path imagePath = Paths.get(imageDirectory + "/" + testId + ".jpg");

        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        return "/images/" + testId + ".jpg"; // Replace with your image endpoint
    }
}
