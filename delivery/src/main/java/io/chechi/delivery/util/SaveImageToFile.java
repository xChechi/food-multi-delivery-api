package io.chechi.delivery.util;

import io.chechi.delivery.entity.Restaurant;
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

    public String saveToFile (Integer entityId, String entityName, MultipartFile file) throws IOException {

        String fileName = sanitizeFileName(entityName) + "_" + entityId + ".jpg";        // file name to be name_id.jpg

        Path imagePath = Paths.get(imageDirectory + "/" + fileName);

        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

        return "/images/" + fileName; // Replace with your image endpoint
    }

    public String imageUrlFor(Integer entityId, String entityName) {
        String sanitizedRestaurantName = sanitizeFileName(entityName);
        String fileName = sanitizedRestaurantName + "_" + entityId + ".jpg";

        return "/images/" + fileName;
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9]", "_");
    }
}
