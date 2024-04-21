package com.bfox.xunbao.common.storage;

/**
 * 存储连接配置
 * @author eden
 * @date 2024/4/21 19:04:04
 */
abstract class StorageConnectionConfiguration {

    private StorageProperties properties;

    StorageConnectionConfiguration(StorageProperties properties) {
        this.properties = properties;
    }

    StorageProperties getProperties() {
        return properties;
    }
}
