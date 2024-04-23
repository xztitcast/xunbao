package com.bfox.xunbao.common.storage.connection.qiniu;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author eden
 * @date 2024/4/23 22:05:05
 */
public class QiniuClientHandler implements StorageClient {

    private QiniuClientConfiguration configuration;

    public QiniuClientHandler(QiniuClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String putObject(InputStream is, long size, String extName) throws Exception {
        UploadManager uploadManager = new UploadManager(new Configuration(Region.autoRegion()));
        String token = Auth.create(configuration.getAccessKey(), configuration.getSecretKey()).uploadToken(configuration.getBucket());
        uploadManager.put(is, extName, token, null, null);
        return "";
    }

    @Override
    public String putObject(byte[] content, String extName) throws Exception {
        return putObject(new ByteArrayInputStream(content), content.length, extName);
    }

    @Override
    public void deleteObject(String url) throws Exception {
        String replace = url.replace(configuration.getDomian(), "").replace(configuration.getBucket().concat("/"), "");
        BucketManager bucketMapper = new BucketManager(Auth.create(configuration.getAccessKey(), configuration.getSecretKey()), new Configuration(Region.autoRegion()));
        bucketMapper.delete(configuration.getBucket(), replace);
    }
}
