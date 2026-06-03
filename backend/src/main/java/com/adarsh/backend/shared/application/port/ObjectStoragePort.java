package com.adarsh.backend.shared.application.port;

import org.springframework.web.multipart.MultipartFile;

public interface ObjectStoragePort {

    String uploadFile(MultipartFile file, String folder);

    void deleteFile(String fileUrl);
}
