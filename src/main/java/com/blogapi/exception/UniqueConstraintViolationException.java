package com.blogapi.exception;

public class UniqueConstraintViolationException extends RuntimeException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }

    public UniqueConstraintViolationException(Throwable cause) {
        super(cause);
    }
}
