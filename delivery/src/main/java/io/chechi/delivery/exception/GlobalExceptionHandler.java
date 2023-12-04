package io.chechi.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotExistException.class)
    public ResponseEntity<String> handlerRestaurantNotFound (RestaurantNotExistException message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageConversionException.class)
    public ResponseEntity<String> handlerImageConversion (ImageConversionException message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotExistException.class)
    public ResponseEntity<String> handlerReviewNotExist (ReviewNotExistException message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handlerCategoryNotFound (CategoryNotFoundException message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExtraNotFoundException.class)
    public ResponseEntity<String> handlerExtraNotFound (ExtraNotFoundException message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }
}
