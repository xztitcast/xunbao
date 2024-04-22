package com.bfox.xunbao.common.storage.connection.minio;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;

import java.io.ByteArrayInputStream;
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
    public String putObject(InputStream inputStream, long size, String extName) throws Exception {
        PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(this.bucket).object(extName).stream(inputStream, size, -1).build();
        this.minioClient.putObject(objectArgs);
        StringBuilder sb = new StringBuilder(this.point);
        sb.append(this.bucket).append("/").append(extName);
        return sb.toString();
    }

    @Override
    public String putObject(byte[] content, String extName) throws Exception {
        return putObject(new ByteArrayInputStream(content), content.length, extName);
    }

    @Override
    public void deleteObject(String name) throws Exception {
        String replace = name.replace(this.point, "").replace(this.bucket.concat("/"), "");
        RemoveObjectArgs objectArgs = RemoveObjectArgs.builder().bucket(this.bucket).object(replace).build();
        this.minioClient.removeObject(objectArgs);
    }
}
