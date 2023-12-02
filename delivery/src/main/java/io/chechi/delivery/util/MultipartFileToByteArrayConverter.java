package io.chechi.delivery.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class MultipartFileToByteArrayConverter {

    public byte[] convert(MultipartFile multipartFile) throws IOException {
        return multipartFile.getBytes();
    }
}
