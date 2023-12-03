package io.chechi.delivery.exception;

public class ReviewNotExistException extends RuntimeException {
    public ReviewNotExistException(String message) {
        super(message);
    }
}
