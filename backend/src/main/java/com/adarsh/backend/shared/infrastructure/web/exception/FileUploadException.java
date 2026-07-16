package com.adarsh.backend.shared.infrastructure.web.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String message) {
        super(message);
    }
}
