package com.bfox.xunbao.common.storage.connection.qcloud;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;

/**
 * 腾讯云存储客户端工厂
 * @Author eden
 * @Date 2024/4/23 20:35
 */
public class QcloudClientFactory implements StorageClientFactory {

    private QcloudClientConfiguration configuration;

    public QcloudClientFactory(QcloudClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public StorageClient createStorageClient() {
        return new QcloudClientHandler(configuration);
    }
}
