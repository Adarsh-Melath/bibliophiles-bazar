package com.adarsh.backend.shared.presentation.constant;

public final class FileControllerConstants {

    // Base Path
    public static final String BASE_PATH = "/api/v1/media";

    // Endpoints
    public static final String UPLOAD_URL_PATH = "/upload-url/{fileName}/{contentType}";
    public static final String UPLOAD_URLS_PATH = "/upload-urls";
    public static final String DOWNLOAD_URL_PATH = "/download-url";

    private FileControllerConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
