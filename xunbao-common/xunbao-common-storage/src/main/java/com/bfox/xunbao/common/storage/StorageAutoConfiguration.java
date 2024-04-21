package com.bfox.xunbao.common.storage;

import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.core.StorageTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 存储自动装载配置
 * @author eden
 * @date 2024/4/21 19:18:18
 */
@ConditionalOnClass(StorageProperties.class)
@EnableConfigurationProperties(StorageProperties.class)
@Import({ MinioConnectionConfiguration.class })
public class StorageAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "storageTemplate")
    @ConditionalOnSingleCandidate(StorageClientFactory.class)
    StorageTemplate storageTemplate(StorageClientFactory storageClientFactory) {
        return new StorageTemplate(storageClientFactory);
    }
}
