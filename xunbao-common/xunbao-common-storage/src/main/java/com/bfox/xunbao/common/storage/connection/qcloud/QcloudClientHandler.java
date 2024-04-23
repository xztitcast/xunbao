package com.bfox.xunbao.common.storage.connection.qcloud;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 腾讯云存储客户端
 * @Author eden
 * @Date 2024/4/23 20:33
 */
public class QcloudClientHandler implements StorageClient {

    private QcloudClientConfiguration configuration;

    public QcloudClientHandler(QcloudClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String putObject(InputStream is, long size, String extName) throws Exception {
        BasicCOSCredentials credentials = new BasicCOSCredentials(configuration.getAccessKey(), configuration.getSecretKey());
        COSClient cosClient = new COSClient(credentials, new ClientConfig(new Region(configuration.getRegion())));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(is.available());
        PutObjectRequest request = new PutObjectRequest(configuration.getBucket(), extName, is, metadata);
        cosClient.putObject(request);
        cosClient.shutdown();
        return configuration.getDomain().concat(extName);
    }

    @Override
    public String putObject(byte[] content, String extName) throws Exception {
        return putObject(new ByteArrayInputStream(content), content.length, extName);
    }

    @Override
    public void deleteObject(String url) throws Exception {
        String replace = url.replace(configuration.getDomain(), "").replace(configuration.getBucket().concat("/"), "");
        BasicCOSCredentials credentials = new BasicCOSCredentials(configuration.getAccessKey(), configuration.getSecretKey());
        COSClient cosClient = new COSClient(credentials, new ClientConfig(new Region(configuration.getRegion())));
        cosClient.deleteObject(configuration.getBucket(), replace);
    }
}
