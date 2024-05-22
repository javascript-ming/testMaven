package com.testJava.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CosUploadService {

    @Autowired
    private COSClient cosClient;

    public PutObjectResult uploadFile(String bucketName, String cosFilePath, File localFile) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, cosFilePath, localFile);
        return cosClient.putObject(putObjectRequest);
    }
}
