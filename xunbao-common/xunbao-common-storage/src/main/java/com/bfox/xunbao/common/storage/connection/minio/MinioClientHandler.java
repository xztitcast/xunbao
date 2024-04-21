package com.bfox.xunbao.common.storage.connection.minio;

import com.bfox.xunbao.common.storage.MinioConnectionConfiguration;
import com.bfox.xunbao.common.storage.connection.StorageClient;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.InputStream;

/**
 * Minio客户端
 * @author eden
 * @date 2024/4/21 20:17:17
 */
public class MinioClientHandler implements StorageClient {

    private String point;

    private String bucket;

    private MinioClient minioClient;

    public MinioClientHandler(MinioClientConfiguration configuration) {
        this.minioClient = MinioClient.builder()
                .endpoint(configuration.getPoint())
                .credentials(configuration.getAccessKey(), configuration.getSecretKey())
                .region(configuration.getRegion())
                .build();
        this.point = configuration.getPoint();
        this.bucket = configuration.getBucket();
    }
    @Override
    public void putObject(InputStream inputStream, long size, String extName) {
        PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(this.bucket).object(extName).stream(inputStream, size, -1).build();
        try {
            this.minioClient.putObject(objectArgs);
        }catch (Exception e) {

        }
    }

    @Override
    public void putObject(byte[] content, String extName) {

    }

    @Override
    public void deleteObject() {

    }
}
