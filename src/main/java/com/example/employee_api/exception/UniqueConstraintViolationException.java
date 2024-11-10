package com.example.employee_api.exception;

public class UniqueConstraintViolationException extends ConstraintViolationException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}