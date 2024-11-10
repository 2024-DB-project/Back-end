package com.example.employee_api.exception;

public class ForeignKeyConstraintViolationException extends ConstraintViolationException {
    public ForeignKeyConstraintViolationException(String message) {
        super(message);
    }
}
