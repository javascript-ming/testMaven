package com.testJava.service;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CosUploadService {

    @Autowired
    private COSClient cosClient;
    // 20M以下文件上传
    // cosFilePath可用来查询文件
    public PutObjectResult uploadFile(String bucketName, String cosFilePath, File localFile) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, cosFilePath, localFile);
        return cosClient.putObject(putObjectRequest);
    }

}
