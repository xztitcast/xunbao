package com.bfox.xunbao.common.storage.connection;

/**
 * @author eden
 * @date 2024/4/21 19:08:08
 */
public interface StorageClientFactory {

    /**
     *
     * @return
     */
    StorageClient createStorageClient();
}
