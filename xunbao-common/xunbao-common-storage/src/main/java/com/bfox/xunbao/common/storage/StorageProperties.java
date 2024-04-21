package com.bfox.xunbao.common.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 存储配置
 * @author eden
 * @date 2024/4/21 19:15:15
 */
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    /**
     * 请求域名
     */
    private String domain;

    /**
     * 请求秘钥
     */
    private String accessKey;

    /**
     * 请求秘钥
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 端点(可以是区域region)
     */
    private String point;

    /**
     * 客户端类型
     */
    private ClientType clientType;

    /**
     * Type of Redis client to use.
     */
    public enum ClientType {

        /**
         * 阿里云
         */
        ALIYUN,

        /**
         * 腾讯云
         */
        QCLOUD,

        /**
         * 七牛云
         */
        QINIU,

        /**
         * Minio
         */
        MINIO

    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
