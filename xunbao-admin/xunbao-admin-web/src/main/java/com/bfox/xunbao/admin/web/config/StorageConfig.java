package com.bfox.xunbao.admin.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 云存储配置
 * @Author eden
 * @Date 2024/4/17 18:24
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageConfig {

    /**
     * 请求地址
     */
    private String domain;

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 访问密钥
     */
    private String secretKey;

    /**
     * 端点也可以是区域
     */
    private String point;

    /**
     * 桶的名称
     */
    private String bucketName;
}
