package com.bfox.xunbao.common.storage.connection.aliyun;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;

/**
 * 阿里云客户端工厂
 * @Author eden
 * @Date 2024/4/23 17:51
 */
public class AliyunClientFactory implements StorageClientFactory {

    private AliyunClientConfiguration configuration;

    public AliyunClientFactory(AliyunClientConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public StorageClient createStorageClient() {
        return new AliyunClientHandler(configuration);
    }
}
