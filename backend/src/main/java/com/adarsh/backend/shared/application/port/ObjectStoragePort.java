package com.adarsh.backend.shared.application.port;

import com.adarsh.backend.shared.application.dto.PresignedResult;

import java.util.List;
import java.util.Map;

public interface ObjectStoragePort {

    PresignedResult generateUploadPresignedUrl(String fileName, String contentType);

    List<PresignedResult> generateUploadPresignedUrls(Map<String,String> files);

    String generateDownloadPresignedUrl(String fileName);
}
