package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

@Component
public class MinioUtil {

    @Value("${minio.defaultBucket}")
    private String defaultBucket;
    @Value("${minio.creativeBucket}")
    private String creativeBucket;
    @Resource
    private MinioClient minioClient;

    private static final Logger logger = LoggerFactory.getLogger(MinioUtil.class);
    private static final String contentType = "image/jpeg";
    private static final String contentType2 ="text/html";

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String uploadFileToDefault(MultipartFile file) {
        String randomFileName = UUID.randomUUID().toString();
        logger.info(file.getContentType());
        String fileName="张朵是sb";
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(defaultBucket).contentType(file.getContentType()).object("/"+randomFileName+"/"+fileName).stream(file.getInputStream(), file.getSize(), -1).build();
            minioClient.putObject(
                    putObjectArgs);

            return randomFileName;

        } catch (ErrorResponseException | XmlParserException | ServerException | NoSuchAlgorithmException |
                 IOException | InvalidResponseException | InvalidKeyException | InternalException |
                 InsufficientDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    public String uploadFileToCreative(MultipartFile file) {
        String randomFileName = UUID.randomUUID().toString();
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(defaultBucket).contentType(file.getContentType()).object(randomFileName).stream(file.getInputStream(), file.getSize(), -1).build();
            minioClient.putObject(
                    putObjectArgs);

            return randomFileName;

        } catch (ErrorResponseException | XmlParserException | ServerException | NoSuchAlgorithmException |
                 IOException | InvalidResponseException | InvalidKeyException | InternalException |
                 InsufficientDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @return
     */
    public boolean uploadStream(InputStream inputStream) {
        String random = UUID.randomUUID().toString();
        try {
            //13299125
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(defaultBucket).contentType(contentType).object(random).stream(inputStream, -1, -1).build();
            minioClient.putObject(
                    putObjectArgs);
            return true;

        } catch (ErrorResponseException | XmlParserException | ServerException | NoSuchAlgorithmException |
                 IOException | InvalidResponseException | InvalidKeyException | InternalException |
                 InsufficientDataException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 文件下载
     * @param objectName
     * @return
     */
    public InputStream downloadInputStream(String objectName) {
        InputStream stream = null;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder().bucket(defaultBucket).object(objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
        return stream;
    }

    /**
     * 删除文件
     * @param objectName
     * @return
     */
    public boolean deleteFile(String objectName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(defaultBucket).object(objectName).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
        return true;
    }
}
