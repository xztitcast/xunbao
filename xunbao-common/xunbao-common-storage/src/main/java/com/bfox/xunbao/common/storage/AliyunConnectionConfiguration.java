package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.connection.aliyun.AliyunClientConfiguration;
import com.bfox.xunbao.common.storage.connection.aliyun.AliyunClientFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云连接配置
 * @Author eden
 * @Date 2024/4/23 20:20
 */
@Configuration
@ConditionalOnMissingBean(StorageClientFactory.class)
@ConditionalOnProperty(name = "storage.client-type", havingValue = "aliyun")
public class AliyunConnectionConfiguration extends StorageConnectionConfiguration {

    AliyunConnectionConfiguration(StorageProperties properties) {
        super(properties);
    }

    @Bean
    AliyunClientFactory storageClientFactory() {
        return createStorageClientFactory();
    }

    private AliyunClientFactory createStorageClientFactory() {
        AliyunClientConfiguration configuration = new AliyunClientConfiguration();
        configuration.setDomain(getProperties().getDomain());
        configuration.setBucket(getProperties().getBucketName());
        configuration.setEndpoint(getProperties().getPoint());
        configuration.setAccessKey(getProperties().getAccessKey());
        configuration.setSecretKey(getProperties().getSecretKey());
        return new AliyunClientFactory(configuration);
    }
}
