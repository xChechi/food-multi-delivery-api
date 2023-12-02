package io.chechi.delivery.exception;

public class RestaurantNotExistException extends RuntimeException {
    public RestaurantNotExistException(String message) {
        super(message);
    }
}
