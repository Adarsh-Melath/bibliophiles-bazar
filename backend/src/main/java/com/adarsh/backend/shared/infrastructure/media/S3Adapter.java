package com.adarsh.backend.shared.application.port;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.adarsh.backend.shared.application.port.ObjectStoragePort;
import com.adarsh.backend.shared.domain.exception.MediaUploadException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
@RequiredArgsConstructor
public class S3Adapter implements ObjectStoragePort {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Override
    public String uploadFile(MultipartFile file, String folder) {
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(folder)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException ex) {
            throw new MediaUploadException("Failed to upload file to S3");
        }

        return "https://" + bucketName + ".s3" + "." + region + ".amazonaws.com/" + folder;
    }

    @Override
    public void deleteFile(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }

    @Override
    public String extractKeyFromUrl(String fileUrl){
        // URL format: https://bucket.s3.region.amazonaws.com/profiles/123-timestamp
        // Key is everything after the domain
        return fileUrl.substring(fileUrl.indexOf(".amazonaws.com/") + ".amazonaws.com/".length());
    }
}
