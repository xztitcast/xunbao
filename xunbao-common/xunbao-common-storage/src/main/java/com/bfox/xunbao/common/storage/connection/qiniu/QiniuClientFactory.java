package com.bfox.xunbao.common.storage.connection.qiniu;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;

/**
 * @author eden
 * @date 2024/4/23 22:05:05
 */
public class QiniuClientFactory implements StorageClientFactory {

    private QiniuClientConfiguration configuration;

    public QiniuClientFactory(QiniuClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public StorageClient createStorageClient() {
        return null;
    }
}
