package com.ndm.api.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(final String message) {
        super(message);
    }
}
