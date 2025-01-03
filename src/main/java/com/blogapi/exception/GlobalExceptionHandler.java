package com.blogapi.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

/*
    # Writing a controller advice
        Developers tested out many ways to make a controller advice class work. After hard work, they found the following points to take into account:
        1. Create your own exception classes. Despite Spring provides many classes that represent common exceptions
                in an application, it is better practice to write your own or extending those existing.
        2. One controller advice class per application. It is a good idea to have all exception handlers in
                a single class instead of annotating multiple ones with @ControllerAdvice.
        3. Write a handleException method. This one is meant to be annotated with @ExceptionHandler and will handle
                all exceptions declared in it and then will delegate to a specific handler method.
        4. Add one method handler per exception. Imagine you want to handle a UserNotFoundException, then create
                a handleUserNotFoundException.
        5. Create a method that sends the response to the user. Handler methods are meant to do the logic to treat
                a given exception, then they will call the method that sends the respond.
                This method will receive a list of errors as body and the specific HTTP status.
        */

    @ExceptionHandler({UniqueConstraintViolationException.class, RuntimeException.class})
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UniqueConstraintViolationException) {
            HttpStatus status = HttpStatus.CONFLICT;
            UniqueConstraintViolationException ucvex = (UniqueConstraintViolationException) ex;

            return handleUniqueConstraintViolationException(ucvex, headers, status, request);
        } else if (ex instanceof RuntimeException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            RuntimeException rex = (RuntimeException) ex;

            return handleRuntimeException(rex, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    protected ResponseEntity<String> handleUniqueConstraintViolationException(UniqueConstraintViolationException ex,
                                                                              HttpHeaders headers, HttpStatus status,
                                                                              WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, errors.toString(), headers, status, request);
    }

    protected ResponseEntity<String> handleRuntimeException(RuntimeException ex,
                                                            HttpHeaders headers, HttpStatus status,
                                                            WebRequest request) {
        List<String> errorMessages = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, errorMessages.toString(), headers, status, request);
    }

    protected ResponseEntity<String> handleExceptionInternal(Exception ex, String body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
