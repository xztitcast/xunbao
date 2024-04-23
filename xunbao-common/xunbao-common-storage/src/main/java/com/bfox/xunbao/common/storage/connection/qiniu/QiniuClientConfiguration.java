package com.bfox.xunbao.common.storage.connection.qiniu;

/**
 * 七牛云客户端配置
 * @author eden
 * @date 2024/4/23 22:06:06
 */
public class QiniuClientConfiguration {

    private String domian;

    private String bucket;

    private String accessKey;

    private String secretKey;

    public String getDomian() {
        return domian;
    }

    public void setDomian(String domian) {
        this.domian = domian;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
