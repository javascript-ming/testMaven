package com.testJava.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class CosMutipartUploadService {
    @Autowired
    private COSClient cosClient;
    private String cosFilePath;
    private String bucketName;
    private File localFile;
    private static final long partSize = 10 * 1024 * 1024; // 定义分片Size

    // 20M以上文件的切片上传
    public CompleteMultipartUploadResult MultipartUpload(String bucketName, String cosFilePath, File localFile) {
        this.bucketName = bucketName;
        this.cosFilePath = cosFilePath;
        this.localFile = localFile;
        try {
            String uploadId = multipartUpload();
            List<PartETag> partETags = uploadPart(uploadId);
            return completePart(uploadId, partETags);
        } catch (CosServiceException cse) {
            throw cse;
        } catch (CosClientException cce) {
            throw cce;
        }
    }
    public String multipartUpload() {
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(this.bucketName, this.cosFilePath);
        // 设置存储类型, 默认是标准(Standard), 低频(Standard_IA), 归档(Archive)
        request.setStorageClass(StorageClass.Standard);
        try {
            InitiateMultipartUploadResult initResult = cosClient.initiateMultipartUpload(request);
            // 获取uploadid
            String uploadId = initResult.getUploadId();
            System.out.println("succeed to init multipart upload, uploadid:" + uploadId);
            return uploadId;
        } catch (CosServiceException e) {
            throw e;
        } catch (CosClientException e) {
            throw e;
        }
    }
    // 分块上传(上传某一个分片的数据)
    private List<PartETag> uploadPart(String uploadId) {
        boolean userTrafficLimit = false;
        List<PartETag> partETags = new LinkedList<>();

        try (FileInputStream fis = new FileInputStream(localFile)) {
            long fileLength = localFile.length();
            long partCount = (fileLength + partSize - 1) / partSize; // 计算切片数量，向上取整

            for (int partNumber = 1; partNumber <= partCount; partNumber++) {
                long start = (partNumber - 1) * partSize;
                long end = Math.min(start + partSize - 1, fileLength - 1);

                byte[] buffer = new byte[(int) (end - start + 1)];
                fis.skip(start);
                fis.read(buffer, 0, buffer.length);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(bucketName);
                uploadPartRequest.setKey(cosFilePath);
                uploadPartRequest.setUploadId(uploadId);
                uploadPartRequest.setInputStream(new ByteArrayInputStream(buffer));
                uploadPartRequest.setPartSize(buffer.length);
                uploadPartRequest.setPartNumber(partNumber);

                if (userTrafficLimit) {
                    uploadPartRequest.setTrafficLimit(8 * 1024 * 1024);
                }

                UploadPartResult uploadPartResult = cosClient.uploadPart(uploadPartRequest);
                PartETag partETag = uploadPartResult.getPartETag();
                partETags.add(partETag);
                System.out.println("succeed to upload part, partNum:" + uploadPartRequest.getPartNumber());
            }
        } catch (IOException  | CosClientException e) {
            throw new RuntimeException("Error uploading part", e);
        }

        return partETags;
    }
    // complete完成分片上传
    private CompleteMultipartUploadResult completePart(String uploadId, List<PartETag> partETags) {
        // uploadid(通过initiateMultipartUpload或者ListMultipartUploads获取)
        // 分片上传结束后，调用complete完成分片上传
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, cosFilePath, uploadId, partETags);
        try {
            CompleteMultipartUploadResult completeResult =
                    this.cosClient.completeMultipartUpload(completeMultipartUploadRequest);
            System.out.println("succeed to complete multipart upload");
            return completeResult;
        } catch (CosServiceException e) {
            throw e;
        } catch (CosClientException e) {
            throw e;
        }
    }

    // 终止分块上传
    private void abortPartUpload(String uploadId) {
        // uploadid(通过initiateMultipartUpload或者ListMultipartUploads获取)
        AbortMultipartUploadRequest abortMultipartUploadRequest = new AbortMultipartUploadRequest(bucketName, cosFilePath, uploadId);
        try {
            cosClient.abortMultipartUpload(abortMultipartUploadRequest);
            System.out.println("succeed to abort multipart upload, uploadid:" + uploadId);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
    }
}
