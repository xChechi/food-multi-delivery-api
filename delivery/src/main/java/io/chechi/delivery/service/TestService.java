package io.chechi.delivery.service;

import io.chechi.delivery.dto.TestRequest;
import io.chechi.delivery.dto.TestResponse;
import org.springframework.web.multipart.MultipartFile;

public interface TestService {

    TestResponse addTest (TestRequest testRequest);
}
