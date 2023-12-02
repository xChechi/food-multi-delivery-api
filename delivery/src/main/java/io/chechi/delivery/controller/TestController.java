package io.chechi.delivery.controller;

import io.chechi.delivery.dto.TestRequest;
import io.chechi.delivery.dto.TestResponse;
import io.chechi.delivery.dto.restaurant.RestaurantRequest;
import io.chechi.delivery.service.TestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
//AllArgsConstructor
public class TestController {

    public TestController(TestService testService) {
        this.testService = testService;
    }

    private final TestService testService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestResponse> addTest(@ModelAttribute @RequestBody @Valid TestRequest testRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.addTest(testRequest));
    }

    /*
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestResponse> addTest(@ModelAttribute TestRequest testRequest) {
        MultipartFile file = testRequest.getFile();
        String name = testRequest.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.addTest(name, file));
    }

     */

}
