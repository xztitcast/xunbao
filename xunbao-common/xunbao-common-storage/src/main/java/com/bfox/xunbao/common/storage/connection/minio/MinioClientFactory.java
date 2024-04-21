package com.bfox.xunbao.common.storage.connection.minio;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;

/**
 * Minio客户端工厂
 * @author eden
 * @date 2024/4/21 19:08:08
 */
public class MinioClientFactory implements StorageClientFactory {

    private final MinioClientConfiguration configuration;

    public MinioClientFactory(MinioClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public StorageClient createStorageClient() {
        return new MinioClientHandler(this.configuration);
    }
}
