package io.chechi.delivery.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

@Component
public class CompressBase64String {

    public String convert (String base64Data) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
            gzipOutputStream.write(decodedBytes);
        }

        byte[] compressedBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(compressedBytes);
    }
}
