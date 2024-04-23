package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.qcloud.QcloudClientConfiguration;
import com.bfox.xunbao.common.storage.connection.qcloud.QcloudClientFactory;
import org.springframework.context.annotation.Bean;

/**
 * @Author eden
 * @Date 2024/4/23 20:53
 */
public class QcloudConnectionConfiguration extends StorageConnectionConfiguration {

    QcloudConnectionConfiguration(StorageProperties properties) {
        super(properties);
    }

    @Bean
    public QcloudClientFactory storageClientFactory() {
        return createStorageClientFactory();
    }

    private QcloudClientFactory createStorageClientFactory() {
        QcloudClientConfiguration configuration = new QcloudClientConfiguration();
        configuration.setDomain(getProperties().getDomain());
        configuration.setBucket(getProperties().getBucketName());
        configuration.setRegion(getProperties().getPoint());
        configuration.setAccessKey(getProperties().getAccessKey());
        configuration.setSecretKey(getProperties().getSecretKey());
        return new QcloudClientFactory(configuration);
    }
}
