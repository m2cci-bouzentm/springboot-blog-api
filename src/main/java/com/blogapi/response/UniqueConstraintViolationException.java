package com.blogapi.response;

public class UniqueConstraintViolationException extends RuntimeException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }

    public UniqueConstraintViolationException(Throwable cause) {
        super(cause);
    }
}
