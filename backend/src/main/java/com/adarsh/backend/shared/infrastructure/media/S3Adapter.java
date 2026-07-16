package com.adarsh.backend.shared.infrastructure.media;

import com.adarsh.backend.shared.application.port.ObjectStoragePort;
import com.adarsh.backend.shared.application.dto.PresignedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3Adapter implements ObjectStoragePort {
    private final S3Presigner s3Presigner;
    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.region}")
    private String region;

    @Override
    public PresignedResult generateUploadPresignedUrl(String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucket).key(fileName).contentType(contentType).build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder().signatureDuration(Duration.ofMinutes(10)).putObjectRequest(putObjectRequest).build();
        String imageUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + fileName;
        String uploadUrl = s3Presigner.presignPutObject(presignRequest).url().toString();
        return new PresignedResult(imageUrl, uploadUrl);
    }

    public List<PresignedResult> generateUploadPresignedUrls(Map<String, String> files) {
        return files.entrySet().stream().map(entry -> generateUploadPresignedUrl(entry.getKey(), entry.getValue())).toList();
    }

    @Override
    public String generateDownloadPresignedUrl(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucket).key(fileName).build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder().signatureDuration(Duration.ofMinutes(10)).getObjectRequest(getObjectRequest).build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }
}
