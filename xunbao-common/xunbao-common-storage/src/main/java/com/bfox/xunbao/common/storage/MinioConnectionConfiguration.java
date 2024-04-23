package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.connection.minio.MinioClientConfiguration;
import com.bfox.xunbao.common.storage.connection.minio.MinioClientFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Minio存储连接配置
 * @author eden
 * @date 2024/4/21 19:05:05
 */
@Configuration
@ConditionalOnMissingBean(StorageClientFactory.class)
@ConditionalOnProperty(name = "storage.client-type", havingValue = "minio")
public class MinioConnectionConfiguration extends StorageConnectionConfiguration {

    MinioConnectionConfiguration(StorageProperties properties) {
        super(properties);
    }

    @Bean
    MinioClientFactory storageClientFactory() {
        return createStorageClientFactory();
    }

    private MinioClientFactory createStorageClientFactory() {
        MinioClientConfiguration configuration = new MinioClientConfiguration();
        configuration.setPoint(getProperties().getDomain());
        configuration.setRegion(getProperties().getPoint());
        configuration.setBucket(getProperties().getBucketName());
        configuration.setAccessKey(getProperties().getAccessKey());
        configuration.setSecretKey(getProperties().getSecretKey());
        return new MinioClientFactory(configuration);
    }
}
