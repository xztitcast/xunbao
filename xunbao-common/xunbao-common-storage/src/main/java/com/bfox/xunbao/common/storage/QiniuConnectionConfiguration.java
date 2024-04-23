package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.connection.qiniu.QiniuClientConfiguration;
import com.bfox.xunbao.common.storage.connection.qiniu.QiniuClientFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云连接配置
 * @author eden
 * @date 2024/4/23 22:20:20
 */
@Configuration
@ConditionalOnMissingBean(StorageClientFactory.class)
@ConditionalOnProperty(name = "storage.client-type", havingValue = "qiniu")
public class QiniuConnectionConfiguration extends StorageConnectionConfiguration{

    QiniuConnectionConfiguration(StorageProperties properties) {
        super(properties);
    }

    @Bean
    QiniuClientFactory storageClientFactory() {
        return createStorageClientFactory();
    }

    public QiniuClientFactory createStorageClientFactory() {
        QiniuClientConfiguration configuration = new QiniuClientConfiguration();
        configuration.setDomian(getProperties().getDomain());
        configuration.setBucket(getProperties().getBucketName());
        configuration.setAccessKey(getProperties().getAccessKey());
        configuration.setSecretKey(getProperties().getSecretKey());
        return new QiniuClientFactory(configuration);
    }
}
