package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.connection.qcloud.QcloudClientConfiguration;
import com.bfox.xunbao.common.storage.connection.qcloud.QcloudClientFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云连接配置
 * @Author eden
 * @Date 2024/4/23 20:53
 */
@Configuration
@ConditionalOnMissingBean(StorageClientFactory.class)
@ConditionalOnProperty(name = "storage.client-type", havingValue = "qcloud")
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
