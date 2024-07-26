package com.bfox.xunbao.common.storage.connection.qcloud;

/**
 * 腾讯云客户端配置
 * @Author eden
 * @Date 2024/4/23 20:34
 */
public class QcloudClientConfiguration {

    private String domain;

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String region;

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

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
