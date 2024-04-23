package com.bfox.xunbao.common.storage.connection.aliyun;

import com.aliyun.oss.OSSClient;
import com.bfox.xunbao.common.storage.connection.StorageClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云客户端处理
 * @Author eden
 * @Date 2024/4/23 17:58
 */
public class AliyunClientHandler implements StorageClient {

    private AliyunClientConfiguration configuration;

    public AliyunClientHandler(AliyunClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String putObject(InputStream is, long size, String extName) throws Exception {
        OSSClient ossClient = new OSSClient(configuration.getEndpoint(), configuration.getAccessKey(), configuration.getSecretKey());
        ossClient.putObject(configuration.getBucket(), extName, is);
        ossClient.shutdown();
        return this.configuration.getDomain().concat(extName);
    }

    @Override
    public String putObject(byte[] content, String extName) throws Exception {
        return putObject(new ByteArrayInputStream(content), content.length, extName);
    }

    @Override
    public void deleteObject(String url) throws Exception {
        String replace = url.replace(configuration.getDomain(), "").replace(configuration.getBucket().concat("/"), "");
        OSSClient ossClient = new OSSClient(configuration.getEndpoint(), configuration.getAccessKey(), configuration.getSecretKey());
        ossClient.deleteObject(configuration.getBucket(), replace);
        ossClient.shutdown();
    }
}
