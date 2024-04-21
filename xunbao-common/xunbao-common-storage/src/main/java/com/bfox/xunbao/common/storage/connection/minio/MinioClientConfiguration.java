package com.bfox.xunbao.common.storage.connection.minio;

/**
 * Mino客户端配置
 * @author eden
 * @date 2024/4/21 20:54:54
 */
public class MinioClientConfiguration {

    private String point;

    private String bucket;

    private String accessKey;

    private String secretKey;

    private String region;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
