package com.sejong.capstone.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {
    private final AmazonS3 s3client;
    private final String bucketName;

    public S3Service(@Value("${cloud.aws.s3.bucket}") String bucketName, AmazonS3 s3client) {
        this.bucketName = bucketName;
        this.s3client = s3client;
    }

    public void uploadFile(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
    }

    public S3Object downloadFile(String fileName) {
        return s3client.getObject(new GetObjectRequest(bucketName, fileName));
    }
    public String getFileUrl(String fileName) {
        return s3client.getUrl(bucketName, fileName).toString();
    }
}
