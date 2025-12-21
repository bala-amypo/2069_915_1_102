package com.example.demo.exception;

/**
 * Custom validation exception extending IllegalArgumentException.
 * Use required keywords in messages so tests can assert them.
 */
public class ValidationException extends IllegalArgumentException {
    public ValidationException(String message) {
        super(message);
    }
}