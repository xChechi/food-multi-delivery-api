package io.chechi.delivery.controller;

import io.chechi.delivery.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

}
