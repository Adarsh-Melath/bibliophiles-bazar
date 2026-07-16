package com.adarsh.backend.shared.infrastructure.web.exception;

public class FileDownloadException extends RuntimeException {
    public FileDownloadException(String message) {
        super(message);
    }
}
