package com.adarsh.backend.shared.presentation.controller;

import com.adarsh.backend.shared.application.dto.PresignedResult;
import com.adarsh.backend.shared.application.port.ObjectStoragePort;
import com.adarsh.backend.shared.presentation.constant.FileControllerConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(FileControllerConstants.BASE_PATH)
@RequiredArgsConstructor
public class FileController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(FileController.class);

    private static final String UPLOAD_URL_REQUEST = "Received request for upload presigned URL for fileName={}";
    private static final String UPLOAD_URL_SUCCESS = "Upload presigned URL generated successfully for fileName={}";
    private static final String UPLOAD_URLS_REQUEST = "Received request for multiple upload presigned URLs, count={}";
    private static final String UPLOAD_URLS_SUCCESS = "Multiple upload presigned URLs generated successfully, count={}";
    private static final String DOWNLOAD_URL_REQUEST = "Received request for download presigned URL for fileName={}";
    private static final String DOWNLOAD_URL_SUCCESS = "Download presigned URL generated successfully for fileName={}";

    private final ObjectStoragePort objectStoragePort;

    @PostMapping(FileControllerConstants.UPLOAD_URL_PATH)
    public ResponseEntity<PresignedResult> getUploadPresignedUrl(@RequestParam String fileName, @RequestParam String contentType) {
        logger.info(UPLOAD_URL_REQUEST, fileName);

        PresignedResult presignedResult = objectStoragePort.generateUploadPresignedUrl(fileName, contentType);

        logger.info(UPLOAD_URL_SUCCESS, fileName);

        return ResponseEntity.ok(presignedResult);
    }

    @PostMapping(FileControllerConstants.UPLOAD_URLS_PATH)
    public ResponseEntity<List<PresignedResult>> getUploadPresignedUrls(@RequestBody Map<String, String> files) {
        logger.info(UPLOAD_URLS_REQUEST, files.size());

        List<PresignedResult> results = objectStoragePort.generateUploadPresignedUrls(files);

        logger.info(UPLOAD_URLS_SUCCESS, results.size());

        return ResponseEntity.ok(results);
    }

    @GetMapping(FileControllerConstants.DOWNLOAD_URL_PATH)
    public ResponseEntity<String> getDownloadPresignedUrl(@RequestParam String fileName) {
        logger.info(DOWNLOAD_URL_REQUEST, fileName);

        String downloadUrl = objectStoragePort.generateDownloadPresignedUrl(fileName);

        logger.info(DOWNLOAD_URL_SUCCESS, fileName);

        return ResponseEntity.ok(downloadUrl);
    }
}
